package com.example.yunwen.testdemo1022.ui.Cook.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.base.BaseActivity;
import com.example.yunwen.testdemo1022.constants.Constants;
import com.example.yunwen.testdemo1022.mvp.cookdetail.CookDetailPresenter;
import com.example.yunwen.testdemo1022.mvp.cookdetail.ICookDetailView;
import com.example.yunwen.testdemo1022.network.bean.CookMenuQueryBean;
import com.example.yunwen.testdemo1022.network.bean.CookMenuSearchBean;
import com.example.yunwen.testdemo1022.ui.Cook.Fragment.adapter.CookDetailAdapter;
import com.example.yunwen.testdemo1022.ui.Cook.Fragment.adapter.HomeAdapter;
import com.example.yunwen.testdemo1022.ui.Cook.entity.MultipleItem;
import com.example.yunwen.testdemo1022.utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

public class CookBookActivity extends BaseActivity<CookDetailPresenter> implements ICookDetailView {

    private Toolbar toolbar;
    private RecyclerView recyclerview_list;
    private List<MultipleItem> mDataList = new ArrayList<>();
    private CookDetailAdapter cookDetailAdapter;
    private CookMenuSearchBean.Lists cookdata;
    private ImageView imgv_bg;

    public static void start(Context context, CookMenuSearchBean.Lists lists) {
        Intent intent = new Intent(context, CookBookActivity.class);
        intent.putExtra("cookdata", lists);
        context.startActivity(intent);
    }


    @Override
    protected void initPresenter() {
       mPresenter = new CookDetailPresenter();
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        cookdata =  getIntent().getParcelableExtra("cookdata");
        String menuid = cookdata.getMenuid();
        mPresenter.getCookDetailData(Constants.BASEURL_COOKBOOKS, "288305d30cae0",menuid);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_cookbook;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        toolbar = findViewById(R.id.toolbar);
        recyclerview_list = findViewById(R.id.recyclerview_list);
        imgv_bg = findViewById(R.id.imgv_bg);

        setToobars();
    }

    private void setToobars() {
        BarUtils.setStatusBarAlpha(this,Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        ViewGroup.MarginLayoutParams toolLayoutParams = (ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
        toolLayoutParams.height = BarUtils.getStatusBarHeight() + BarUtils.getActionBarHeight();
        toolbar.setLayoutParams(toolLayoutParams);
        toolbar.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0);
        toolbar.requestLayout();
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(cookdata.getName());

        ImageUtil.showImg(this,cookdata.getThumbnail(),R.drawable.load_error,R.drawable.load_error,(ImageView) imgv_bg,1f);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void doBusiness() {
        recyclerview_list.setHasFixedSize(true);
        recyclerview_list.setLayoutManager(new LinearLayoutManager(this));
        cookDetailAdapter = new CookDetailAdapter(this,mDataList);
        recyclerview_list.setAdapter(cookDetailAdapter);
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
    public void getCookDetailData(CookMenuQueryBean bean) {
        LogUtils.e("getCookDetailData---" + bean.getResult().getName());
        String sumary = bean.getResult().getRecipe().getSumary();
        String title = bean.getResult().getRecipe().getTitle();
        String ingredients = bean.getResult().getRecipe().getIngredients();
        String method = bean.getResult().getRecipe().getMethod();

        mDataList.add(new MultipleItem(MultipleItem.Cook_Detail_Item_Type_CookMan,title, sumary,this));
        mDataList.add(new MultipleItem(MultipleItem.Cook_Detail_Item_Type_Header,ingredients,this ));
        mDataList.add(new MultipleItem(MultipleItem.Cook_Detail_Item_Type_Step,method,this ));
        cookDetailAdapter.notifyDataSetChanged();
    }
}
