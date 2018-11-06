package com.example.yunwen.testdemo1022.ui.Cook.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class TaskFragment extends BaseFragment {


    private RecyclerView recyclerview_task;
    private List<String> mDataList = new ArrayList<>();

    @Override
    public void initData(@Nullable Bundle bundle) {
        for (int i = 0; i < 10; i++) {
            mDataList.add("item" + i);
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_task;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        recyclerview_task = contentView.findViewById(R.id.recyclerview_task);
        recyclerview_task.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void doBusiness() {
//        BaseQuickAdapter homeAdapter = new HomeAdapter(R.layout.fragment_task_item_view, mDataList);
//
//        recyclerview_task.setAdapter(homeAdapter);
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
    public void onClick(View view) {

    }

    @Override
    protected void initPresenter() {

    }
}
