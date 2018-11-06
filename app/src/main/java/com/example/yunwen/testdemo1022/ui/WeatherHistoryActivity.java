package com.example.yunwen.testdemo1022.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.base.BaseActivity;
import com.example.yunwen.testdemo1022.base.BaseBackActivity;
import com.example.yunwen.testdemo1022.constants.Constants;
import com.example.yunwen.testdemo1022.mvp.weather.IWeatherHistoryView;
import com.example.yunwen.testdemo1022.mvp.weather.WeatherHistoryPresenter;
import com.example.yunwen.testdemo1022.network.bean.WeatherHistoryBean;
import com.example.yunwen.testdemo1022.network.bean.WeatherOldBean;

import java.util.List;

public class WeatherHistoryActivity extends BaseBackActivity<WeatherHistoryPresenter> implements IWeatherHistoryView {

    private TextView tvWeatherResult;
    private TextView tv_weatherold_result;

    public static void start(Context context) {
        Intent starter = new Intent(context, WeatherHistoryActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new WeatherHistoryPresenter();
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        mPresenter.weatherhistory(Constants.BASEURL_WEATHER,"e9d44ae2b3184d325deb06102b1b99e6", "1");
        mPresenter.weatherold(Constants.BASEURL_WEATHER,"e9d44ae2b3184d325deb06102b1b99e6", "1", "2018-10-23");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_weatherhistory;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        tvWeatherResult = findViewById(R.id.tv_weather_result);
        tv_weatherold_result = findViewById(R.id.tv_weatherold_result);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    public void onError(Throwable e) {
        tvWeatherResult.setText(e.getMessage());
        e.printStackTrace();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onWeatherData(WeatherHistoryBean bean) {
        if (bean.getErrCode() == 0) {
            List<WeatherHistoryBean.DataBean> beanList = bean.getBeanList();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < beanList.size(); i++) {
                stringBuilder.append(beanList.get(i).getCity_name() + "-");
            }
            tvWeatherResult.setText(stringBuilder);
        } else {
            tvWeatherResult.setText(bean.getErrMess());
        }
    }

    @Override
    public void onWeatherOldData(WeatherOldBean bean) {
        if (bean.getErrCode() == 0) {
            WeatherOldBean.Result result = bean.getResult();
            LogUtils.e("result ===" + result);
            tv_weatherold_result.setText(result.toString());
        } else {
            tv_weatherold_result.setText(bean.getErrMess());
        }
    }
}
