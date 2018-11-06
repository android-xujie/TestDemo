package com.example.yunwen.testdemo1022.mvp.base;

import com.example.yunwen.testdemo1022.network.http.BaseView;

public class BasePresenter<T extends BaseView> implements IPresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
