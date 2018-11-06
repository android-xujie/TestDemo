package com.example.yunwen.testdemo1022.network.callback;

public interface ApiCallback<T> {
    void onNext(T t);

    void onError(Throwable throwable);

    void onCompleted();
}
