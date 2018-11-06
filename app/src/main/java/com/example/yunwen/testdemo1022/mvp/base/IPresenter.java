package com.example.yunwen.testdemo1022.mvp.base;

import com.example.yunwen.testdemo1022.network.http.BaseView;

public interface IPresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();
}
