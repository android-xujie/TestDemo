package com.example.yunwen.testdemo1022.ui.Cook.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.base.BaseBackActivity;
import com.example.yunwen.testdemo1022.ui.Cook.view.ChannelView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CookChannelActivity extends BaseBackActivity implements ChannelView.OnChannelListener {

    private ChannelView channelView;

    public static void start(Context context) {
        Intent intent = new Intent(context, CookChannelActivity.class);
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
        return R.layout.activity_cookchannel;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        channelView = findViewById(R.id.channelView);
    }

    @Override
    public void doBusiness() {
        String[] myChannel = {"要闻", "视频", "新时代", "娱乐", "体育", "军事", "NBA", "国际", "科技", "财经", "汽车", "电影", "游戏", "独家", "房产",
                "图片", "时尚", "呼和浩特", "三打白骨精"};
        String[] recommendChannel0 = {"综艺", "美食", "育儿", "冰雪", "必读", "政法网事", "都市",
                "NFL", "韩流"};
        String[] recommendChannel = {"问答", "文化", "佛学", "股票", "动漫", "理财", "情感", "职场", "旅游"};
        String[] recommendChannel2 = {"家居", "电竞", "数码", "星座", "教育", "美容", "电视剧",
                "搏击", "健康"};

        Map<String, String[]> channels = new LinkedHashMap<>();
        channels.put("我的频道", myChannel);
        channels.put("推荐频道", recommendChannel0);
        channels.put("国内", recommendChannel);
        channels.put("国外", recommendChannel2);

        channelView.setFixedChannel(-1);
        channelView.setChannels(channels);
//        channelView.setMyChannelBelong(1, 2);
//        channelView.setMyChannelBelong(1, 3);
//        channelView.setMyChannelBelong(5, 4);
//        channelView.setMyChannelBelong(7, 3);
//        channelView.setMyChannelBelong(0, 2);
        channelView.setChannelNormalBackground(R.drawable.bg_channel_normal);
        channelView.setChannelSelectedBackground(R.drawable.bg_channel_selected);
        channelView.setChannelFocusedBackground(R.drawable.bg_channel_focused);
        channelView.setOnChannelItemClickListener(this);
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

    @Override
    public void channelItemClick(int itemId, String channel) {
        LogUtils.e(itemId + ".." + channel);
    }

    @Override
    public void channelFinish(List<String> channels) {
        LogUtils.e(channels.toString());
    }
}
