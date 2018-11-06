package com.example.yunwen.testdemo1022.pattern.factory.method;

import com.blankj.utilcode.util.LogUtils;

/**
 * 产品实现
 */

public class BaiduMapView extends IMapView {
    @Override
    public void onStart() {
        LogUtils.e("百度地图onStart");
    }

    @Override
    public void onResume() {
        LogUtils.e("百度地图onResume");
    }

    @Override
    public void onDestory() {
        LogUtils.e("百度地图onDestory");
    }
}
