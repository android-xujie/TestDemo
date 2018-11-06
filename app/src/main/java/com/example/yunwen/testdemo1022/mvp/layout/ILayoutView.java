package com.example.yunwen.testdemo1022.mvp.layout;

import com.example.yunwen.testdemo1022.network.bean.CookSearchBean;
import com.example.yunwen.testdemo1022.network.http.BaseView;


public interface ILayoutView extends BaseView {
    void onLayoutData(CookSearchBean bean);
}
