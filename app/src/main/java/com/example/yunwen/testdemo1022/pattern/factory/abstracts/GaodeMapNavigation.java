package com.example.yunwen.testdemo1022.pattern.factory.abstracts;

import com.blankj.utilcode.util.LogUtils;

public class GaodeMapNavigation extends AbsMapNavigation {
    @Override
    public void turnByTurn() {
        LogUtils.e("高德地图导航功能路线规划");
    }
}
