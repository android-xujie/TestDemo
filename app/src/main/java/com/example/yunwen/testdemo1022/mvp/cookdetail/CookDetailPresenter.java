package com.example.yunwen.testdemo1022.mvp.cookdetail;

import com.example.yunwen.testdemo1022.mvp.base.BasePresenter;
import com.example.yunwen.testdemo1022.network.Api;
import com.example.yunwen.testdemo1022.network.bean.CookMenuQueryBean;
import com.example.yunwen.testdemo1022.network.callback.SimpleCallback;

public class CookDetailPresenter extends BasePresenter<ICookDetailView> {

    public void getCookDetailData(String baseurl, String key, String id) {
        Api.getCookMenuQuery(baseurl, key, id, new SimpleCallback<CookMenuQueryBean>(mView){
            @Override
            public void onNext(CookMenuQueryBean bean) {
                mView.getCookDetailData(bean);
            }
        });
    }
}
