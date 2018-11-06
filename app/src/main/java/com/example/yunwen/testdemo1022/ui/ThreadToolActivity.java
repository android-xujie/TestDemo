package com.example.yunwen.testdemo1022.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.base.BaseBackActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ThreadUtils工具类的使用，
 */

public class ThreadToolActivity extends BaseBackActivity {


    public static void start(Context context) {
        Intent intent = new Intent(context, ThreadToolActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_threadpool;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        findViewById(R.id.bt_thread1).setOnClickListener(this);
        findViewById(R.id.bt_thread2).setOnClickListener(this);
        findViewById(R.id.bt_thread3).setOnClickListener(this);

    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.bt_thread1:
                //线程池分固定线程池，缓冲线程池，单线程池，周期线程等
                //在固定的线程池中执行给定的任务，size是线程池大小,task是要执行的任务，priority是线程执行的优先级（1-10）
                ThreadUtils.executeByFixed(2,new TestTask(),1);
                //在给定的延迟之后，在一个固定的线程池中执行给定的任务,delay是延迟的时间，unit是时间单位
//                ThreadUtils.executeByFixedWithDelay(10, new TestTask(), 10, TimeUnit.SECONDS);
                //以固定的速率在固定的线程池中执行给定的任务。period是间隔时间,unit是时间单位
//                ThreadUtils.executeByFixedAtFixRate(10, new TestTask(), 10, TimeUnit.SECONDS);

                ExecutorService fixedPool = ThreadUtils.getFixedPool(10);
                break;
            case R.id.bt_thread2:
                //默认使用Java虚拟机的可用的处理器数量作为线程池的大小相关参数进行设置
                //io线程池大小是（2 * CPU_COUNT + 1）CPU_COUNT是可以的cpu数量
                ThreadUtils.executeByIo(new TestTask());
                break;
            case R.id.bt_thread3:
                //cpu线程池大小是（CPU_COUNT + 1）CPU_COUNT是可以的cpu数量
                ThreadUtils.executeByCpu(new TestTask());
                break;
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onCompleted() {

    }

    class TestTask extends ThreadUtils.Task<String> {
        @Nullable
        @Override
        public String doInBackground() throws Throwable {

            LogUtils.e("线程池测试-----11");
            Thread.sleep(5000);
            LogUtils.e("线程池测试-----22");
            return "results";
        }

        @Override
        public void onSuccess(@Nullable String result) {
            LogUtils.e("线程池测试-----" + "onSuccess" + "----" + result );
        }

        @Override
        public void onCancel() {
            LogUtils.e("线程池测试-----" + "onCancel");
        }

        @Override
        public void onFail(Throwable t) {
            LogUtils.e("线程池测试-----" + "onFail" + "----" + t );
        }
    }

}
