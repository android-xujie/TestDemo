package com.example.yunwen.testdemo1022.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.yunwen.testdemo1022.mvp.base.BasePresenter;
import com.example.yunwen.testdemo1022.network.http.BaseView;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/10/24
 *     desc  : base about activity
 * </pre>
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IBaseView,BaseView {

    protected View mContentView;
    protected Activity mActivity;

    /**
     * 上次点击时间
     */
    private long lastClick = 0;
    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (ScreenUtils.isPortrait()) {
//            ScreenUtils.adaptScreen4VerticalSlide(this, 720);
//        } else {
//            ScreenUtils.adaptScreen4HorizontalSlide(this, 720);
//        }
        super.onCreate(savedInstanceState);
        mActivity = this;
        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        Bundle bundle = getIntent().getExtras();
        initData(bundle);
        setBaseView(bindLayout());
        initView(savedInstanceState, mContentView);
        doBusiness();
    }

    protected abstract void initPresenter();

    @SuppressLint("ResourceType")
    protected void setBaseView(@LayoutRes int layoutId) {
        if (layoutId <= 0) return;
        setContentView(mContentView = LayoutInflater.from(this).inflate(layoutId, null));
    }

    /**
     * 判断是否快速点击
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    private boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 200) {
            lastClick = now;
            return false;
        }
        return true;
    }

    @Override
    public void onClick(final View view) {
        if (!isFastClick()) onWidgetClick(view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
