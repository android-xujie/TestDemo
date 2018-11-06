package com.example.yunwen.testdemo1022.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.base.BaseActivity;
import com.example.yunwen.testdemo1022.base.BaseBackActivity;
import com.example.yunwen.testdemo1022.mvp.login.ILoginView;
import com.example.yunwen.testdemo1022.mvp.login.LoginPresenter;
import com.example.yunwen.testdemo1022.network.bean.LoginBean;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {



    private TextView tv_result;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }



    @Override
    protected void initPresenter() {
        mPresenter = new LoginPresenter();
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        mPresenter.login("","wangjinqiang", "qq", "0", "token");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        tv_result = findViewById(R.id.tv_result);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    public void onError(Throwable e) {
        tv_result.setText(e.getMessage());
        e.printStackTrace();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onLoginData(LoginBean bean) {
        if (bean.getErrCode() == 200) {
            tv_result.setText(bean.getData().getNickname());
        } else {
            tv_result.setText("errCode:" + String.valueOf(bean.getErrCode()));
        }
    }
}
