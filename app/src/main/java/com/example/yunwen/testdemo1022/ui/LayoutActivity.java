package com.example.yunwen.testdemo1022.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.base.BaseActivity;
import com.example.yunwen.testdemo1022.base.BaseBackActivity;
import com.example.yunwen.testdemo1022.constants.Constants;
import com.example.yunwen.testdemo1022.frames.data.User;
import com.example.yunwen.testdemo1022.mvp.layout.ILayoutView;
import com.example.yunwen.testdemo1022.mvp.layout.LayoutPresenter;
import com.example.yunwen.testdemo1022.mvp.weather.WeatherHistoryPresenter;
import com.example.yunwen.testdemo1022.network.bean.CookSearchBean;
import com.example.yunwen.testdemo1022.network.bean.WeatherProvinceBean;
import com.gturedi.views.StatefulLayout;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;

/**
 * 针对不同状态的网络数据返回值，显示不同的界面加载状态，使用的三方库
 * 界面加载UI状态：com.github.gturedi:stateful-layout:1.2.1
 */

public class LayoutActivity extends BaseBackActivity<LayoutPresenter> implements ILayoutView {

    private StatefulLayout mLlStateful;
    private TextView tv_layout;


    public static void start(Context context) {
        Intent intent = new Intent(context, LayoutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new LayoutPresenter();
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        mPresenter.getCookBooks(Constants.BASEURL_COOKBOOK, "秘制红烧肉", "json", "", "", 0, "efcc30370973714b39cbf36560142001");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_layout;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        mLlStateful = findViewById(R.id.stateful);
        tv_layout = findViewById(R.id.tv_layout);

    }

    @Override
    public void doBusiness() {
        mLlStateful.showLoading();
    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onLayoutData(CookSearchBean bean) {
        if (bean.getResultcode().equals("200")) {
            for (int i = 0; i < bean.getResult().getData().get(0).getSteps().size(); i++) {
                LogUtils.e("Steps:--" + bean.getResult().getData().get(0).getSteps().get(i).getImg());
            }
            LogUtils.e(bean.getResultcode() + "---" + bean.getResult().getData().get(0).getSteps().get(0).getImg());
        } else {
            LogUtils.e("error:" + bean.getErrMess());
        }

    }
}
