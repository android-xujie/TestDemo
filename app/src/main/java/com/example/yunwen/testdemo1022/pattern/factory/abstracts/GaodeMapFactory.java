package com.example.yunwen.testdemo1022.pattern.factory.abstracts;



public class GaodeMapFactory extends AbsMapFactory {
    @Override
    public AbsMapView createMapView() {
        return new GaodeMapView();
    }

    @Override
    public AbsMapNavigation createMapNavigation() {
        return new GaodeMapNavigation();
    }

    @Override
    public AbsMapLocation createMapLocation() {
        return new GaodeMapLocation();
    }
}
