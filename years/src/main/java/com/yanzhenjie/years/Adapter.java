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

import android.content.Context;

import com.yanzhenjie.wheel.adapters.AbstractWheelTextAdapter;

import java.util.List;

/**
 * Created by YanZhenjie on 2018/1/17.
 */
public class Adapter extends AbstractWheelTextAdapter {

    private List<String> mItemList;

    protected Adapter(Context context, List<String> itemList) {
        super(context, R.layout.years_item, R.id.tv_item);
        this.mItemList = itemList;
    }

    @Override
    protected CharSequence getItemText(int position) {
        return mItemList.get(position);
    }

    @Override
    public int getItemsCount() {
        return mItemList == null ? 0 : mItemList.size();
    }
}