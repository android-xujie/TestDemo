package com.example.yunwen.testdemo1022.pattern.factory.method;

/**
 * 抽象工厂
 */

public abstract class AbsMapFactory {
    public abstract <T extends IMapView> T createMapView(Class<T> clzz);
}
