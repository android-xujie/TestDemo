package com.example.yunwen.testdemo1022.pattern.factory.abstracts;

import com.blankj.utilcode.util.LogUtils;

public class GaodeMapView extends AbsMapView {
    @Override
    public void onStart() {
        LogUtils.e("高德地图调用了onStart");
    }

    @Override
    public void onResume() {
        LogUtils.e("高德地图调用了onResume");
    }

    @Override
    public void onDestory() {
        LogUtils.e("高德地图调用了onDestory");
    }
}
