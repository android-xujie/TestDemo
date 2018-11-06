package com.example.yunwen.testdemo1022.pattern.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例模式，，不管采取何种方案，（线程安全，，延时加载，，序列化和反序列化安全）
 */

public class Singletons {

    /**
     * 饿汉模式1，，，无法做到延时创建对象，，想要尽可能的延时加载，减小负载
     */
//   private static Singletons singletons = new Singletons();
//
//    public Singletons() {}
//
//    public static Singletons getSingleton1() {
//        returns singletons;
//    }
    //饿汉式变化
//    private static Singletons instance = null;
//    static {
//        instance = new Singletons();
//    }
//    private Singletons(){}
//    private static Singletons getInstance(){
//        returns instance;
//    }

    /**
     * 懒汉模式（线程不安全），可能出现同时创建多个对象的并发情况
      */
//    private static Singletons instance = null;
//    public Singletons() { }
//    public static Singletons getSingleton() {
//        if (instance == null) {
//            instance = new Singletons();
//        }
//        returns instance;
//    }

    /**
     * 懒汉模式（线程安全，但是耗时）加锁
      */
//    private static volatile Singletons singletons = null;
//    public Singletons() { }
//    public static Singletons getSingleton(){
//        synchronized (Singletons.class) {
//            if (singletons == null) {
//                singletons = new Singletons();
//            }
//        }
//        returns singletons;
//    }

    /**
     * 懒汉模式（双重校验锁，，兼顾线程安全和效率的写法）（推荐）
     */
//    private static volatile Singletons singletons = null;
//    public Singletons() {
//    }
//    public static Singletons getSingletons() {
//        if (singletons == null) {
//            synchronized (Singletons.class) {
//                if (singletons == null) {
//                    singletons = new Singletons();
//                }
//            }
//        }
//        returns singletons;
//    }

    /**
     * 静态内部类写法（推荐）
     */
//    private static class Holder {
//        private static Singletons singletons = new Singletons();
//    }
//    public Singletons() { }
//    public static Singletons getSingleton() {
//        returns Holder.singletons;
//    }



    /**
     * 容器实现单例模式
      */
    private static Map<String, Object> objMap = new HashMap<>();
    public Singletons() { }
    public static void registerService(String key, Object instance) {
        if (!objMap.containsKey(key)) {
            objMap.put(key, instance);
        }
    }
    public static Object getService(String key) {
        return objMap.get(key);
    }
}
