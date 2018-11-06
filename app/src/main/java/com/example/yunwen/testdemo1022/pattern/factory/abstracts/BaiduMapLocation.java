package com.example.yunwen.testdemo1022.pattern.factory.abstracts;

import com.blankj.utilcode.util.LogUtils;

public class BaiduMapLocation extends AbsMapLocation {
    @Override
    public void location() {
        LogUtils.e("百度地图定位...");
    }
}
