package com.example.yunwen.testdemo1022.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.base.BaseActivity;
import com.example.yunwen.testdemo1022.ui.widget.CircleCountDownView;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends BaseActivity {

    private TextView tv_time;
    private int index = 6;
    private CircleCountDownView countDownView;

    @Override
    protected void initPresenter() {

    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        BarUtils.setStatusBarAlpha(this, Color.TRANSPARENT);
        tv_time = findViewById(R.id.tv_time);
        countDownView = findViewById(R.id.circlecountdownview);

    }

    @Override
    public void doBusiness() {
//        setTimeTextView();

        countDownView.setStartCountValue(5);
        countDownView.setCountDownListener(new CircleCountDownView.CountDownListener() {
            @Override
            public void onCountDownFinish() {
                LogUtils.e("onCountDownFinish-------");
                MainActivity.start(SplashActivity.this);
                finish();
            }

            @Override
            public void restTime(long restTime) {
                LogUtils.e("restTime: " + restTime);
            }
        });
        countDownView.setAnimationInterpolator(new CircleCountDownView.AnimationInterpolator() {
            @Override
            public float getInterpolation(float inputFraction) {
                return inputFraction * inputFraction;
            }
        });
        countDownView.startCountDown();
    }

    private void setTimeTextView() {
        ThreadUtils.executeByFixedAtFixRate(10, new ThreadUtils.Task<Object>() {
            @Nullable
            @Override
            public String doInBackground() throws Throwable {
                index--;
                runOnUiThread(new Thread(){
                    @Override
                    public void run() {

                        tv_time.setText("倒计时（" + index + "）");
                    }
                });

                LogUtils.e("sss----");

                return null;
            }

            @Override
            public void onSuccess(@Nullable Object result) {


                LogUtils.e("result----");
                if (index == 1) {
                    MainActivity.start(SplashActivity.this);
                    ThreadUtils.cancel(this);
                    finish();
                }

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onFail(Throwable t) {

            }

        }, 1, TimeUnit.SECONDS);
    }

    @Override
    public void onWidgetClick(View view) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onCompleted() {

    }
}
