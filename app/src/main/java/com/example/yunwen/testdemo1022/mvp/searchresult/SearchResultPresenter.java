package com.example.yunwen.testdemo1022.mvp.searchresult;

import com.example.yunwen.testdemo1022.mvp.base.BasePresenter;
import com.example.yunwen.testdemo1022.network.Api;
import com.example.yunwen.testdemo1022.network.bean.CookMenuSearchBean;
import com.example.yunwen.testdemo1022.network.callback.SimpleCallback;

public class SearchResultPresenter extends BasePresenter<ISearchResultView> {
    public void getSearchResultData(String baseurl, String key, String name,  int page, int size) {
        Api.getCookMenuSearchResult(baseurl, key, name, page, size, new SimpleCallback<CookMenuSearchBean>(mView){
            @Override
            public void onNext(CookMenuSearchBean bean) {
                mView.getSearchResultData(bean);

            }
        });
    }
}
