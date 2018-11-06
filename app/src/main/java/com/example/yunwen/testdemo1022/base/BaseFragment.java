package com.example.yunwen.testdemo1022.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yunwen.testdemo1022.mvp.base.BasePresenter;
import com.example.yunwen.testdemo1022.network.http.BaseView;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseView,BaseView {

    private View mRootView;
    public T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initData(savedInstanceState);
        inflaterView(inflater, container);
        initView(savedInstanceState,mRootView);
        doBusiness();
        return mRootView;
    }

    protected abstract void initPresenter();

    private void inflaterView(LayoutInflater inflater, ViewGroup container) {
        if (mRootView == null) {
            mRootView = inflater.inflate(bindLayout(), container, false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
