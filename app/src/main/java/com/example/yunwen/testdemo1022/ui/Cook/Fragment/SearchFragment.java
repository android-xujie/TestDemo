package com.example.yunwen.testdemo1022.ui.Cook.Fragment;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.blankj.utilcode.util.LogUtils;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.ui.Cook.activity.FragmentActivity;
import com.example.yunwen.testdemo1022.ui.Cook.activity.SearchResultActivity;

@SuppressLint("ValidFragment")
public class SearchFragment extends Fragment {
    private static boolean isFirst = true;
    public static Context context;
    private static EditText edit_search;

    @SuppressLint("ValidFragment")
    public SearchFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        isFirst = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_cook_search, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        edit_search = rootView.findViewById(R.id.edit_search);
    }
    public static boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            String s = edit_search.getText().toString();
            LogUtils.e("GameFragmet事件", "OK" + "---" + s + "--" + isFirst);

            if (isFirst) {
                SearchResultActivity.start(context, s);
                isFirst = false;
            }
        }
        return true;
    }


}
