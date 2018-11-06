package com.example.yunwen.testdemo1022.network;

import com.example.yunwen.testdemo1022.constants.Constants;
import com.example.yunwen.testdemo1022.network.bean.CookCategoryBean;
import com.example.yunwen.testdemo1022.network.bean.CookMenuQueryBean;
import com.example.yunwen.testdemo1022.network.bean.CookMenuSearchBean;
import com.example.yunwen.testdemo1022.network.bean.CookSearchBean;
import com.example.yunwen.testdemo1022.network.bean.LoginBean;
import com.example.yunwen.testdemo1022.network.bean.WeatherHistoryBean;
import com.example.yunwen.testdemo1022.network.bean.WeatherOldBean;
import com.example.yunwen.testdemo1022.network.callback.SimpleCallback;
import com.example.yunwen.testdemo1022.network.http.ApiManager;
import com.example.yunwen.testdemo1022.network.http.ApiObserver;
import com.example.yunwen.testdemo1022.network.http.ApiService;


public class Api {

    private static ApiService apiservice;

    public Api() {
        throw new AssertionError();
    }

    private static ApiService getApiService(String baseurl) {
        if (apiservice == null) {
            apiservice = ApiManager.getInstance().getApiService(ApiService.class,baseurl);
        }

        return apiservice;
    }

    public static void login(String baseurl,String username, String password, String type, String google_token, SimpleCallback<LoginBean> callback) {
        ApiObserver.subscribe(getApiService(baseurl).login(username, password, type, google_token), callback);
    }

    public static void weatherhistory(String baseurl,String key, String province_id, SimpleCallback<WeatherHistoryBean> callback) {
        ApiObserver.subscribe(getApiService(baseurl).weatherhistory(key,province_id),callback);
    }

    public static void weatherold(String baseurl,String key, String city_id, String weather_date, SimpleCallback<WeatherOldBean> callback) {
        ApiObserver.subscribe(getApiService(baseurl).weatherold(key,city_id,weather_date),callback);
    }

    public static void getCookBooks(String baseurl, String menu, String dtype, String pn, String rn, int albums,String key, SimpleCallback<CookSearchBean> callback) {
        ApiObserver.subscribe(getApiService(baseurl).cooksearch(menu,dtype,pn,rn,albums,key),callback);
    }

    public static void getCookCategory(String baseurl, String key, SimpleCallback<CookCategoryBean> callback) {
        ApiObserver.subscribe(getApiService(baseurl).cookcategory(key), callback);
    }

    public static void getCookMenuSearch(String baseurl, String key, String cid,  int page, int size, SimpleCallback<CookMenuSearchBean> callback) {
        ApiObserver.subscribe(getApiService(baseurl).cookmenusearchid(key,cid,page,size),callback);
    }

    public static void getCookMenuSearchResult(String baseurl, String key, String name,  int page, int size, SimpleCallback<CookMenuSearchBean> callback) {
        ApiObserver.subscribe(getApiService(baseurl).cookmenusearchname(key,name,page,size),callback);
    }

    public static void getCookMenuQuery(String baseurl, String key, String id, SimpleCallback<CookMenuQueryBean> callback) {
        ApiObserver.subscribe(getApiService(baseurl).cookmenuquery(key,id),callback);
    }
}
