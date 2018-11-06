package com.example.yunwen.testdemo1022.pattern.factory.method;

public class DefaultMapFactory extends AbsMapFactory {

    private static DefaultMapFactory defaultMapFactory;

    public DefaultMapFactory() {
    }

    public static DefaultMapFactory getInstance() {
        if (defaultMapFactory == null) {
            defaultMapFactory = new DefaultMapFactory();
        }
        return defaultMapFactory;
    }

    @Override
    public  <T extends IMapView> T createMapView(Class<T> clzz) {
        try {
            //反射
            return clzz.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
