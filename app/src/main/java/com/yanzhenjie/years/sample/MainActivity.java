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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yanzhenjie.years.YearsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YanZhenjie on 2018/1/16.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        final YearsView yearsView = (YearsView) findViewById(R.id.years_view);

        int[] colors = {Color.parseColor("#993F51B5"), Color.parseColor("#00000000")};
        yearsView.setShadow(colors);

        List<String> yearItemList = new ArrayList<>();
        for (int i = 2000; i < 2050; i++) {
            yearItemList.add(Integer.toString(i));
        }

        List<String> monthItemList = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            monthItemList.add(Integer.toString(i));
        }

        yearsView.setYearItemList(yearItemList);
        yearsView.setMonthItemList(monthItemList);

        findViewById(R.id.btn_year).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText(R.string.app_year);
                yearsView.showYear();
            }
        });
        findViewById(R.id.btn_month).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText(R.string.app_month);
                yearsView.showMonth();
            }
        });
    }
}