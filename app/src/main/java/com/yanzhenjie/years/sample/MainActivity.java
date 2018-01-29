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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
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

        findViewById(R.id.btn_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SampleActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shoeYearsDialog();
            }
        });
    }

    private void shoeYearsDialog() {
        final List<String> yearList = new ArrayList<>();
        for (int i = 1970; i <= 3000; i++) {
            yearList.add(Integer.toString(i));
        }

        String[] monthArray = getResources().getStringArray(R.array.month_list);
        final List<String> monthList = Arrays.asList(monthArray);

        YearsDialog yearsDialog = new YearsDialog(this, yearList, monthList, new YearsDialog.Callback() {
            @Override
            public void onValue(YearsDialog dialog, int yearIndex, int monthIndex) {
                dialog.dismiss();

                String year = yearList.get(yearIndex);
                String month = monthList.get(monthIndex);
                Toast.makeText(MainActivity.this, year + "-" + month, Toast.LENGTH_LONG).show();
            }
        });
        yearsDialog.show();
    }
}