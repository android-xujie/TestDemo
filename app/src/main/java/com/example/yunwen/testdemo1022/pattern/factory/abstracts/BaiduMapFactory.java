package com.example.yunwen.testdemo1022.pattern.factory.abstracts;

public class BaiduMapFactory extends AbsMapFactory {
    @Override
    public AbsMapView createMapView() {
        return new BaiduMapView();
    }

    @Override
    public AbsMapNavigation createMapNavigation() {
        return new BaiduMapNavigation();
    }

    @Override
    public AbsMapLocation createMapLocation() {
        return new BaiduMapLocation();
    }
}
