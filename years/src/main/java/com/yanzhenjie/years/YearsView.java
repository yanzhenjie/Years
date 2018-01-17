/*
 * Copyright © Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.years;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import com.yanzhenjie.wheel.OnWheelChangedListener;
import com.yanzhenjie.wheel.WheelView;
import com.yanzhenjie.wheel.adapters.WheelViewAdapter;

import java.util.List;

/**
 * Created by YanZhenjie on 2018/1/16.
 */
public class YearsView extends FrameLayout {

    private WheelView mYearView;
    private WheelView mMonthView;

    private OnValueChangedListener mValueChangedListener;
    private int mCurrentYear = -1;
    private int mCurrentMonth = -1;

    public YearsView(Context context) {
        this(context, null, 0);
    }

    public YearsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public YearsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupViews();
    }

    private void setupViews() {
        mYearView = new WheelView(getContext());
        mYearView.setLayoutParams(new LayoutParams(-1, -2));
        addView(mYearView);
        mYearView.setTag(false);

        mMonthView = new WheelView(getContext());
        mMonthView.setLayoutParams(new LayoutParams(-1, -2));
        addView(mMonthView);
        mMonthView.setTag(false);

        mYearView.addChangingListener(mWheelChangedListener);
        mMonthView.addChangingListener(mWheelChangedListener);
    }

    private OnWheelChangedListener mWheelChangedListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView wheelView, int oldView, int newValue) {
            if (wheelView == mYearView) {
                mCurrentYear = newValue;
            } else if (wheelView == mMonthView) {
                mCurrentMonth = newValue;
            }

            if (mValueChangedListener != null) {
                mValueChangedListener.onChanged(YearsView.this, mCurrentYear, mCurrentMonth);
            }
        }
    };

    /**
     * Set listener of value.
     */
    public void setValueChangedListener(OnValueChangedListener valueChangedListener) {
        this.mValueChangedListener = valueChangedListener;
    }

    /**
     * Set the top and bottom of the View gradient.
     */
    public void setShadow(int[] colors) {
        GradientDrawable topDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors);
        mYearView.setTopShadow(topDrawable);
        mMonthView.setTopShadow(topDrawable);

        GradientDrawable bottomDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors);
        mYearView.setBottomShadow(bottomDrawable);
        mMonthView.setBottomShadow(bottomDrawable);
    }

    /**
     * Set the color of the middle filter.
     */
    public void setCenterFilter(Drawable centerFilter) {
        mYearView.setCenterFilter(centerFilter);
        mMonthView.setCenterFilter(centerFilter);
    }

    /**
     * Adapter to set the wheel of the year.
     */
    public void setYearAdapter(WheelViewAdapter yearAdapter) {
        mYearView.setAdapter(yearAdapter);
    }

    /**
     * Set the year's data, using the default adapter.
     */
    public void setYearItemList(List<String> yearItemList) {
        mYearView.setAdapter(new Adapter(getContext(), yearItemList));
    }

    /**
     * Adapter to set the wheel of the month.
     */
    public void setMonthAdapter(WheelViewAdapter monthAdapter) {
        mMonthView.setAdapter(monthAdapter);
    }

    /**
     * Set the month's data, using the default adapter.
     */
    public void setMonthItemList(List<String> yearItemList) {
        mMonthView.setAdapter(new Adapter(getContext(), yearItemList));
    }

    /**
     * Show year wheel.
     */
    public void showYear() {
        if (!isVisibleArea(mYearView)) {
            int distance = getXDistance();
            Animator translateYear = translateX(mYearView, -distance, 0);
            Animator translateMonth = translateX(mMonthView, 0, distance);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(translateYear, translateMonth);
            animatorSet.start();
        }
    }

    /**
     * Show month wheel.
     */
    public void showMonth() {
        if (!isVisibleArea(mMonthView)) {
            int distance = getXDistance();
            Animator translateYear = translateX(mYearView, 0, -distance);
            Animator translateMonth = translateX(mMonthView, distance, 0);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(translateYear, translateMonth);
            animatorSet.start();
        }
    }

    /**
     * Is the year wheel visible?
     */
    public boolean isYearVisible() {
        return isVisibleArea(mYearView);
    }

    /**
     * Is the month wheel visible?
     */
    public boolean isMonthVisible() {
        return isVisibleArea(mMonthView);
    }

    /**
     * Set the current index index of the year.
     */
    public void setYearIndex(int itemIndex) {
        mYearView.setCurrentItem(itemIndex);
    }

    /**
     * Get the current item index of the year.
     */
    public int getYearIndex() {
        return mCurrentYear;
    }

    /**
     * Set the current index index of the month.
     */
    public void setMonthIndex(int itemIndex) {
        mMonthView.setCurrentItem(itemIndex);
    }

    /**
     * Get the current item index of the month.
     */
    public int getMonthIndex() {
        return mCurrentMonth;
    }

    private boolean isVisibleArea(View view) {
        int[] viewLocation = new int[2];
        view.getLocationInWindow(viewLocation);
        Rect visibleRect = new Rect();
        getLocalVisibleRect(visibleRect);
        return visibleRect.contains(viewLocation[0], viewLocation[1]);
    }

    private int getXDistance() {
        Rect visibleRect = new Rect();
        getLocalVisibleRect(visibleRect);
        return visibleRect.width();
    }

    private Animator translateX(View view, int fromX, int toX) {
        Animator animator = ObjectAnimator.ofObject(view, "translationX", new FloatEvaluator(), fromX, toX);
        animator.setDuration(300).setInterpolator(new LinearInterpolator());
        return animator;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus && !(Boolean) mMonthView.getTag()) {
            mYearView.setTag(false);
            mYearView.setTranslationX(0);

            mMonthView.setTag(true);
            mMonthView.setTranslationX(getXDistance());
        }
        if (hasWindowFocus && (Boolean) mYearView.getTag()) {
            mYearView.setTag(false);
            mYearView.setTranslationX(-getXDistance());

            mMonthView.setTag(true);
            mMonthView.setTranslationX(0);
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        Parcelable superData = super.onSaveInstanceState();
        bundle.putParcelable("super_data", superData);
        bundle.putBoolean("save_data", isYearVisible());
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        Parcelable superData = bundle.getParcelable("super_data");
        boolean yearVisible = bundle.getBoolean("save_data");
        mMonthView.setTag(!yearVisible);
        mYearView.setTag(!yearVisible);
        super.onRestoreInstanceState(superData);
    }

    public interface OnValueChangedListener {
        /**
         * When the roller's years change.
         */
        void onChanged(YearsView yearsView, int yearValue, int monthValue);
    }
}