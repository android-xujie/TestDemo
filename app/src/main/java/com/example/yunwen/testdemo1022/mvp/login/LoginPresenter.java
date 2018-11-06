package com.example.yunwen.testdemo1022.mvp.login;

import com.example.yunwen.testdemo1022.mvp.base.BasePresenter;
import com.example.yunwen.testdemo1022.network.Api;
import com.example.yunwen.testdemo1022.network.callback.SimpleCallback;
import com.example.yunwen.testdemo1022.network.bean.LoginBean;

public class LoginPresenter extends BasePresenter<ILoginView> {
    public void login(String baseurl,String username, String password, String type, String google_token) {
        Api.login(baseurl,username,password,type,google_token,new SimpleCallback<LoginBean>(mView){
            @Override
            public void onNext(LoginBean bean) {
                mView.onLoginData(bean);
            }
        });
    }
}
