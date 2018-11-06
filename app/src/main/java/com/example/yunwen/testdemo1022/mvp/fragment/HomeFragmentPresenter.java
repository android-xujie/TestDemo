package com.example.yunwen.testdemo1022.mvp.fragment;

import com.example.yunwen.testdemo1022.mvp.base.BasePresenter;
import com.example.yunwen.testdemo1022.network.Api;
import com.example.yunwen.testdemo1022.network.bean.CookCategoryBean;
import com.example.yunwen.testdemo1022.network.bean.CookMenuSearchBean;
import com.example.yunwen.testdemo1022.network.bean.CookSearchBean;
import com.example.yunwen.testdemo1022.network.callback.SimpleCallback;

public class HomeFragmentPresenter extends BasePresenter<IHomeFragmentView> {

    public void getCookBooks(String baseurl, String menu, String dtype, String pn, String rn, int albums,String key) {
        Api.getCookBooks(baseurl,menu,dtype,pn,rn,albums,key,new SimpleCallback<CookSearchBean>(mView){
            @Override
            public void onNext(CookSearchBean cookSearchBean) {
                mView.onHomeData(cookSearchBean);
            }
        });
    }

    public void getCookCategorys(String baseurl, String key) {
        Api.getCookCategory(baseurl, key, new SimpleCallback<CookCategoryBean>(mView){
            @Override
            public void onNext(CookCategoryBean cookCategoryBean) {
                mView.onCategoryData(cookCategoryBean);
            }
        });
    }

    public void getCookMenuSearch(String baseurl, String key, String cid, int page, int size) {
        Api.getCookMenuSearch(baseurl,key,cid,page,size,new SimpleCallback<CookMenuSearchBean>(mView){
            @Override
            public void onNext(CookMenuSearchBean cookMenuSearchBean) {
                mView.onMenuSearchData(cookMenuSearchBean);
            }
        });
    }
}
