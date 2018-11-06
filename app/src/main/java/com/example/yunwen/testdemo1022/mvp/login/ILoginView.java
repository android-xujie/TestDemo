package com.example.yunwen.testdemo1022.mvp.login;

import com.example.yunwen.testdemo1022.network.http.BaseView;
import com.example.yunwen.testdemo1022.network.bean.LoginBean;

public interface ILoginView extends BaseView {
    void onLoginData(LoginBean bean);
}
