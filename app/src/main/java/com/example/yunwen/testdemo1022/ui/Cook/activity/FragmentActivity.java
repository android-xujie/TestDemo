package com.example.yunwen.testdemo1022.ui.Cook.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.base.BaseActivity;

import com.example.yunwen.testdemo1022.ui.Cook.Fragment.AboutFragment;
import com.example.yunwen.testdemo1022.ui.Cook.Fragment.CategoryFragment;
import com.example.yunwen.testdemo1022.ui.Cook.Fragment.GoodCarFragment;
import com.example.yunwen.testdemo1022.ui.Cook.Fragment.HomeFragment;
import com.example.yunwen.testdemo1022.ui.Cook.Fragment.SearchFragment;
import com.example.yunwen.testdemo1022.ui.Cook.Fragment.TaskFragment;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;


public class FragmentActivity extends BaseActivity {

    private ImageView iv_return;
    private ImageView iv_more;
    private TextView tv_title;
    private ImageView iv_search;
    private SearchFragment searchFragment;

    public static void start(Context context) {
        Intent intent = new Intent(context, FragmentActivity.class);
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
        return R.layout.activity_fragment;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        iv_return = findViewById(R.id.iv_return);
        iv_more = findViewById(R.id.iv_more);
        tv_title = findViewById(R.id.tv_title);
        iv_search = findViewById(R.id.iv_search);

//        LinearLayout main_content = findViewById(R.id.main_content);
        iv_return.setOnClickListener(this);
        iv_more.setOnClickListener(this);
        tv_title.setOnClickListener(this);
        iv_search.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {
        //下面是LuseenBottomNavigation的使用
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);

        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("首页", ContextCompat.getColor(this, R.color.firstColor), R.mipmap.ic_account_balance_white_48dp);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("分类", ContextCompat.getColor(this, R.color.secondColor), R.mipmap.ic_list_white_48dp);

        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("任务", ContextCompat.getColor(this, R.color.firstColor), R.mipmap.ic_add_circle_outline_white_48dp);
        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("购物车", ContextCompat.getColor(this, R.color.thirdColor), R.mipmap.ic_add_shopping_cart_white_48dp);

        BottomNavigationItem bottomNavigationItem4 = new BottomNavigationItem
                ("我的", ContextCompat.getColor(this, R.color.colorAccent), R.mipmap.ic_account_box_white_48dp);

        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);
        bottomNavigationView.addTab(bottomNavigationItem2);
        bottomNavigationView.addTab(bottomNavigationItem3);
        bottomNavigationView.addTab(bottomNavigationItem4);

        //为底部导航布局设置点击事件
        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int i) {
                switch (i){
                    case 0:
                        switchToHome();
                        tv_title.setText("首页");
                        iv_search.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        switchToCategory();
                        tv_title.setText("分类");
                        break;
                    case 2:
                        switchToTask();
                        tv_title.setText("任务");
                        break;
                    case 3:
                        switchToGoodCar();
                        tv_title.setText("购物车");
                        break;
                    case 4:
                        switchToAbout();
                        tv_title.setText("我的");
                        break;
                }
            }
        });
        //初始加载首页，即GoodsFragment
        switchToHome();
    }

    private void switchToAbout() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new AboutFragment(),AboutFragment.class.getName()).commit();
    }
    private void switchToCategory() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new CategoryFragment(),CategoryFragment.class.getName()).commit();
    }

    private void switchToTask() {
       getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new TaskFragment(),TaskFragment.class.getName()).commit();
    }

    private void switchToGoodCar() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new GoodCarFragment(),GoodCarFragment.class.getName()).commit();
    }

    private void switchToHome() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content,new HomeFragment(),HomeFragment.class.getName()).commit();
    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.iv_return:
                finish();
                break;
            case R.id.iv_more:

                break;
            case R.id.iv_search:
                clickSearch();
                break;
        }
    }

    public void clickSearch() {
        searchFragment = new SearchFragment(this);
        int fragment_search = getFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, searchFragment, "fragment_search")
                .addToBackStack("fragment:reveal")
                .commit();
    }


    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public boolean  dispatchKeyEvent(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
            /*隐藏软键盘*/
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if(inputMethodManager.isActive()){
                inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
                SearchFragment.onKeyDown(KeyEvent.KEYCODE_ENTER,event);
            }

            return true;
        }
        return super.dispatchKeyEvent(event);
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        LogUtils.e("ActionBar", "OnKey事件");
//        if(searchFragment instanceof SearchFragment){
//            SearchFragment.onKeyDown(keyCode, event);
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
