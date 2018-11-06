package com.example.yunwen.testdemo1022.mvp.searchresult;


import com.example.yunwen.testdemo1022.network.bean.CookMenuSearchBean;
import com.example.yunwen.testdemo1022.network.http.BaseView;

public interface ISearchResultView extends BaseView {
    void getSearchResultData(CookMenuSearchBean bean);
}
