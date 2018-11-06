package com.example.yunwen.testdemo1022.pattern.factory.abstracts;

public abstract class AbsMapFactory {
    public abstract AbsMapView createMapView();

    public abstract AbsMapNavigation createMapNavigation();

    public abstract AbsMapLocation createMapLocation();
}
