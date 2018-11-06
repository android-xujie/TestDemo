package com.example.yunwen.testdemo1022.pattern.factory.method;

import com.blankj.utilcode.util.LogUtils;

/**
 * 产品实现
 */

public class GaodeMapView extends IMapView {
    @Override
    public void onStart() {
        LogUtils.e("高德地图onStart");
    }

    @Override
    public void onResume() {
        LogUtils.e("高德地图onResume");
    }

    @Override
    public void onDestory() {
        LogUtils.e("高德地图onDestory");
    }
}
