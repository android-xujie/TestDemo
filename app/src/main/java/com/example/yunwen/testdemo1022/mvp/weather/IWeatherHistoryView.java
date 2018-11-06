package com.example.yunwen.testdemo1022.mvp.weather;

import com.example.yunwen.testdemo1022.network.bean.WeatherOldBean;
import com.example.yunwen.testdemo1022.network.http.BaseView;
import com.example.yunwen.testdemo1022.network.bean.WeatherHistoryBean;

public interface IWeatherHistoryView extends BaseView{

        void onWeatherData(WeatherHistoryBean bean);

        void onWeatherOldData(WeatherOldBean bean);
}
