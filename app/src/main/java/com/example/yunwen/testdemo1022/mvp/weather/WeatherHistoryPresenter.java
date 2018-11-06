package com.example.yunwen.testdemo1022.mvp.weather;

import com.example.yunwen.testdemo1022.mvp.base.BasePresenter;
import com.example.yunwen.testdemo1022.network.Api;
import com.example.yunwen.testdemo1022.network.bean.WeatherOldBean;
import com.example.yunwen.testdemo1022.network.callback.SimpleCallback;
import com.example.yunwen.testdemo1022.network.bean.WeatherHistoryBean;

public class WeatherHistoryPresenter extends BasePresenter<IWeatherHistoryView> {
    public void weatherhistory(String baseurl,String key, String province_id) {
        Api.weatherhistory(baseurl,key,province_id,new SimpleCallback<WeatherHistoryBean>(mView){
            @Override
            public void onNext(WeatherHistoryBean weatherHistoryBean) {
                mView.onWeatherData(weatherHistoryBean);
            }
        });
    }

    public void weatherold(String baseurl,String key,String city_id,String weather_date) {
        Api.weatherold(baseurl,key, city_id, weather_date, new SimpleCallback<WeatherOldBean>(mView){
            @Override
            public void onNext(WeatherOldBean bean) {
                mView.onWeatherOldData(bean);
            }
        });

    }
}
