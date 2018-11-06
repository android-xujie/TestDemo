package com.example.yunwen.testdemo1022.mvp.layout;

import com.example.yunwen.testdemo1022.mvp.base.BasePresenter;
import com.example.yunwen.testdemo1022.network.Api;
import com.example.yunwen.testdemo1022.network.bean.CookSearchBean;
import com.example.yunwen.testdemo1022.network.callback.SimpleCallback;

public class LayoutPresenter extends BasePresenter<ILayoutView>  {

    public void getCookBooks(String baseurl, String menu, String dtype, String pn, String rn, int albums,String key) {
        Api.getCookBooks(baseurl,menu,dtype,pn,rn,albums,key,new SimpleCallback<CookSearchBean>(mView){
            @Override
            public void onNext(CookSearchBean cookSearchBean) {
                mView.onLayoutData(cookSearchBean);
            }
        });
    }


}
