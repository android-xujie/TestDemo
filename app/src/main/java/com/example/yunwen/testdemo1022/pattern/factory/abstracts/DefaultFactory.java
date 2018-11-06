package com.example.yunwen.testdemo1022.pattern.factory.abstracts;

public class DefaultFactory {

    public static <T extends AbsMapFactory> T creatProduct(Class<T> clzz) {
        AbsMapFactory api = null;
        try {
            api = (AbsMapFactory) Class.forName(clzz.getName()).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return (T)api;
    }
}
