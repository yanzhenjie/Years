# Years
Just select the year and month view on Android, its internal use [AndroidWheel](https://github.com/yanzhenjie/AndroidWheel) implementation.

## Screenshot
<image src="./image/1_en.gif" width="210px"/> <image src="./image/2_en.gif" width="210px"/> <image src="./image/1_cn.gif" width="210px"/> <image src="./image/2_cn.gif" width="210px"/>

Gif's effect may not be very good, this is how it looks when it is quiet.  
<image src="./image/1_cn.png" width="210px"/> <image src="./image/2_cn.png" width="210px"/>  

## Download
* Gradle
```
implementation 'com.yanzhenjie:years:1.0.0'
```

* Maven
```
<dependency>
  <groupId>com.yanzhenjie</groupId>
  <artifactId>years</artifactId>
  <version>1.0.0</version>
</dependency>
```

AndroidWheel requires at minimum Java 7 or Android 4.0(Api level 14) .

## Usage
Initialize YearsView:
```xml
<com.yanzhenjie.years.YearsView
    android:id="@+id/years"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>
```
Or in Java files:
```java
YearsView yearsView = ...;
```

Modify the appearance of the Api:
```java
int colors = new int[]{Color.WHITE, Color.TRANSPARENT};
yearsView.setShadow(colors); // Modify the top and bottom audio.

Drawable drawable = getDrawable(R.drawable.shape_year_filter);
yearsView.setCenterFilter(Drawable); // Modify the middle of the selector.
```

Set the data of the API:
```java
List<String> yearList = ...;
yearsView.setYearItemList(yearList);

List<String> monthList = ...;
yearsView.setMonthItemList(monthList);
```

Or use the adapter to load the data:
```java
yearsView.setYearAdapter(WheelViewAdapter);
yearsView.setMonthAdapter(WheelViewAdapter);
```

Modify the currently selected Item:
```java
int yearIndex = ...;
int monthIndex = ...;
yearsView.setYearIndex(yearIndex);
yearsView.setMonthIndex(monthIndex);
```

Monitor the value of the wheel:
```java
final List<String> yearList = ...;
final List<String> monthList = ...;

...

mYearsView.setValueChangedListener(new YearsView.OnValueChangedListener() {
    @Override
    public void onChanged(YearsView yearsView, int yearValue, int monthValue) {
        String year = yearList.get(yearValue);
        String month = monthList.get(monthValue);
        ...
    }
});
```

Active obtain the value of the wheel:
```java
final List<String> yearList = ...;
final List<String> monthList = ...;

...

int yearIndex = mYearsView.getYearIndex();
int monthIndex = mYearsView.getMonthIndex();
String year = yearList.get(yearValue);
String month = monthList.get(monthValue);
...
```

## ProGuard
If you are using ProGuard you might need to add the following options:
```
-dontwarn com.yanzhenjie.wheel.**
-dontwarn com.yanzhenjie.years.**
```

## License
```text
Copyright 2017 Yan Zhenjie

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```