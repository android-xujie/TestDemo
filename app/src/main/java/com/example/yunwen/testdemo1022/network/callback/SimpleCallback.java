package com.example.yunwen.testdemo1022.network.callback;

import com.example.yunwen.testdemo1022.network.http.BaseView;

public class SimpleCallback<T> implements ApiCallback<T> {

    private BaseView mView;

    public SimpleCallback(BaseView view) {
        this.mView = view;
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        mView.onError(e);
    }

    @Override
    public void onCompleted() {
        mView.onCompleted();
    }
}
