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

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.years.YearsView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by YanZhenjie on 2018/1/29.
 */
public class SampleActivity extends AppCompatActivity {

    private TextView mTvTitle;
    private YearsView mYearsView;

    private TextView mTvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mYearsView = (YearsView) findViewById(R.id.years_view);
        mTvResult = (TextView) findViewById(R.id.tv_result);

        int[] colors = {ContextCompat.getColor(this, R.color.colorAccent), Color.TRANSPARENT};
        mYearsView.setShadow(colors);
        mYearsView.setCenterFilter(ContextCompat.getDrawable(this, R.drawable.shape_year_filter_activity));

        final List<String> yearList = new ArrayList<>();
        for (int i = 1970; i < 3000; i++) {
            yearList.add(Integer.toString(i));
        }

        final List<String> monthList = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            monthList.add(Integer.toString(i));
        }

        mYearsView.setYearItemList(yearList);
        mYearsView.setMonthItemList(monthList);

        findViewById(R.id.btn_year).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvTitle.setText(R.string.app_year_select);
                mYearsView.showYear();
            }
        });
        findViewById(R.id.btn_month).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvTitle.setText(R.string.app_month_select);
                mYearsView.showMonth();
            }
        });

        mYearsView.setValueChangedListener(new YearsView.OnValueChangedListener() {
            @Override
            public void onChanged(YearsView yearsView, int yearValue, int monthValue) {
                String year = yearList.get(yearValue);
                String month = monthList.get(monthValue);
                mTvResult.setText(year + "-" + month);
            }
        });

        findViewById(R.id.btn_obtain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int yearIndex = mYearsView.getYearIndex();
                int monthIndex = mYearsView.getMonthIndex();

                String year = yearList.get(yearIndex);
                String month = monthList.get(monthIndex);
                Toast.makeText(SampleActivity.this, year + "-" + month, Toast.LENGTH_LONG).show();
            }
        });

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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                finish();
                break;
            }
        }
        return true;
    }
}