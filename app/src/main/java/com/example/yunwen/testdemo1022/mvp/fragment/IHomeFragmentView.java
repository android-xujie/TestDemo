package com.example.yunwen.testdemo1022.mvp.fragment;

import com.example.yunwen.testdemo1022.network.bean.CookCategoryBean;
import com.example.yunwen.testdemo1022.network.bean.CookMenuSearchBean;
import com.example.yunwen.testdemo1022.network.bean.CookSearchBean;
import com.example.yunwen.testdemo1022.network.http.BaseView;

public interface IHomeFragmentView extends BaseView {
    void onHomeData(CookSearchBean bean);

    void onCategoryData(CookCategoryBean bean);

    void onMenuSearchData(CookMenuSearchBean bean);
}
