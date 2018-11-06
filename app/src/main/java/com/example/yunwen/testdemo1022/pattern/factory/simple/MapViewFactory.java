package com.example.yunwen.testdemo1022.pattern.factory.simple;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 创建工厂类
 *
 * Android中简单工厂--BitmapFactory,XmlPullParserFactory,CertificateFactory
 */

public class MapViewFactory {

    static enum MapType{
        Baidu,
        Gaode
    }

    private static MapViewFactory mapViewFactory;

    private MapViewFactory() {
    }

    public static MapViewFactory getInstance(){
        if (mapViewFactory == null) {
            mapViewFactory = new MapViewFactory();
            ArrayList<String> list = new ArrayList<>();
            HashSet<String> hashSet = new HashSet<>();
        }
        return mapViewFactory;
    }

    public IMapView getMapVIew(String mapType) {
        IMapView mapView = null;
        switch (mapType) {
            case "Baidu":
                mapView = new BaiduMapView();
                break;
            case "Gaode":
                mapView = new GaodeMapView();
                break;
        }
        return mapView;
    }
}
