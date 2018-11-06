package com.example.yunwen.testdemo1022.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.ui.Cook.activity.FragmentActivity;
import com.example.yunwen.testdemo1022.ui.bomb.ui.PersonActivity;

import cn.bmob.v3.Bmob;


public class MainActivity extends AppCompatActivity {

    private TextView tv_con;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_con = findViewById(R.id.tv_con);

        findViewById(R.id.bt_click_permission_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PermissionActivity.start(MainActivity.this);
            }
        });
        findViewById(R.id.bt_click_login_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.start(MainActivity.this);
            }
        });

        findViewById(R.id.bt_click_weather_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WeatherHistoryActivity.start(MainActivity.this);
            }
        });
        findViewById(R.id.bt_click_threadpool_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThreadToolActivity.start(MainActivity.this);
            }
        });

        findViewById(R.id.bt_click_fragment_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity.start(MainActivity.this);
            }
        });

        findViewById(R.id.bt_click_layout_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutActivity.start(MainActivity.this);
            }
        });

        findViewById(R.id.bt_click_data_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonActivity.start(MainActivity.this);
            }
        });

        //第一：默认初始化
        Bmob.initialize(this, "c426c910e5a76096b485adf8c0d11e6d");

    }


}
