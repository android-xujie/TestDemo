package com.example.yunwen.testdemo1022.pattern.factory.abstracts;

import com.blankj.utilcode.util.LogUtils;

public class GaodeMapLocation extends AbsMapLocation {
    @Override
    public void location() {
        LogUtils.e("高德地图定位...");
    }
}
