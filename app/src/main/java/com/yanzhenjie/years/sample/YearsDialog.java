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
package com.yanzhenjie.years.sample;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanzhenjie.years.YearsView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by YanZhenjie on 2018/1/29.
 */
public class YearsDialog implements View.OnClickListener {

    private Context mContext;
    private Callback mCallback;
    private AlertDialog mDialog;

    private TextView mTvTitle;
    private ImageView mIvBack;

    private YearsView mYearsView;

    private Button mBtnCancel;
    private Button mBtnConfirm;

    public YearsDialog(Context context, List<String> yearList, List<String> monthList, Callback callback) {
        this.mContext = context;
        this.mCallback = callback;

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_sample, null, false);
        mTvTitle = (TextView) view.findViewById(R.id.tv_title);
        mIvBack = (ImageView) view.findViewById(R.id.iv_back);
        mYearsView = (YearsView) view.findViewById(R.id.years);
        mBtnCancel = (Button) view.findViewById(R.id.btn_cancel);
        mBtnConfirm = (Button) view.findViewById(R.id.btn_confirm);

        mIvBack.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);

        mYearsView.setShadow(new int[]{Color.WHITE, Color.TRANSPARENT});
        mYearsView.setCenterFilter(ContextCompat.getDrawable(mContext, R.drawable.shape_year_filter_dialog));

        mYearsView.setYearItemList(yearList);
        mYearsView.setMonthItemList(monthList);

        mDialog = new AlertDialog.Builder(context)
                .setView(view)
                .create();

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        int yearIndex = yearList.indexOf(Integer.toString(year));
        int monthIndex = monthList.indexOf(Integer.toString(month));
        mYearsView.setYearIndex(yearIndex);
        mYearsView.setMonthIndex(monthIndex);
    }

    @Override
    public void onClick(View v) {
        if (v == mIvBack) {
            mTvTitle.setText(R.string.app_year_select);
            mIvBack.setVisibility(View.GONE);
            mYearsView.showYear();
        } else if (v == mBtnCancel) {
            dismiss();
        } else if (v == mBtnConfirm) {
            if (mYearsView.isYearVisible()) {
                mTvTitle.setText(R.string.app_month_select);
                mIvBack.setVisibility(View.VISIBLE);
                mYearsView.showMonth();
            } else {
                mCallback.onValue(this, mYearsView.getYearIndex(), mYearsView.getMonthIndex());
            }
        }
    }

    public void show() {
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public void dismiss() {
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public interface Callback {
        void onValue(YearsDialog dialog, int yearIndex, int monthIndex);
    }
}