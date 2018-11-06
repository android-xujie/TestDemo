package com.example.yunwen.testdemo1022.mvp.cookdetail;

import com.example.yunwen.testdemo1022.network.bean.CookMenuQueryBean;
import com.example.yunwen.testdemo1022.network.http.BaseView;

public interface ICookDetailView extends BaseView {
    void getCookDetailData(CookMenuQueryBean bean);
}
