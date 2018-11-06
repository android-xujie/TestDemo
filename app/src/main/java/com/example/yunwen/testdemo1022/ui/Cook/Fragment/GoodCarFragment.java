package com.example.yunwen.testdemo1022.ui.Cook.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.example.yunwen.testdemo1022.R;

public class GoodCarFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.i("onCreateView");
        View view = inflater.inflate(R.layout.fragment_goodcar, null);
        return view;
    }
}
