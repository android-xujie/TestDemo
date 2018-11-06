package com.example.yunwen.testdemo1022.pattern.factory.simple;

import android.view.View;

import com.blankj.utilcode.util.LogUtils;

public class GaodeMapView implements IMapView {
    @Override
    public View getView() {
        LogUtils.e("调用了高德地图的getView");
        return null;
    }

    @Override
    public void setMapType(MapType mapType) {
        LogUtils.e("调用了高德地图的setMapType");
    }
}
