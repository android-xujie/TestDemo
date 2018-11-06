package com.example.yunwen.testdemo1022.pattern.factory.abstracts;

import com.blankj.utilcode.util.LogUtils;

public class BaiduMapNavigation extends AbsMapNavigation {
    @Override
    public void turnByTurn(){
        LogUtils.e("百度地图导航路线规划");
    }
}
