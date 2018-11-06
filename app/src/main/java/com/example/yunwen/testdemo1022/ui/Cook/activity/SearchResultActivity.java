package com.example.yunwen.testdemo1022.ui.Cook.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.base.BaseActivity;
import com.example.yunwen.testdemo1022.base.BaseBackActivity;
import com.example.yunwen.testdemo1022.constants.Constants;
import com.example.yunwen.testdemo1022.mvp.searchresult.ISearchResultView;
import com.example.yunwen.testdemo1022.mvp.searchresult.SearchResultPresenter;
import com.example.yunwen.testdemo1022.network.bean.CookMenuSearchBean;
import com.example.yunwen.testdemo1022.ui.Cook.Fragment.adapter.HomeAdapter;
import com.gturedi.views.StatefulLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends BaseActivity<SearchResultPresenter> implements ISearchResultView {

    private RecyclerView recyclerview_searchview;
    private List<CookMenuSearchBean.Lists> mDataList = new ArrayList<>();
    private HomeAdapter homeAdapter;
    private SwipeRefreshLayout swipeLayout;
    private StatefulLayout stateful;

    private int totalNum = 0;
    private boolean isMore = false;
    private int index = 1;
    private String searchname;
    private TextView tv_title;

    public static void start(Context context, String s) {
        LogUtils.e(s);
        Intent intent = new Intent(context, SearchResultActivity.class);
        intent.putExtra("searchname", s);
        context.startActivity(intent);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new SearchResultPresenter();
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        searchname = getIntent().getStringExtra("searchname");
        mPresenter.getSearchResultData(Constants.BASEURL_COOKBOOKS, "288305d30cae0", searchname, 1, 20);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_searchresult;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        tv_title = findViewById(R.id.tv_title);
        tv_title.setText("搜索结果");
        recyclerview_searchview = findViewById(R.id.recyclerview_searchview);
        swipeLayout = contentView.findViewById(R.id.swipeLayout);
        stateful = contentView.findViewById(R.id.stateful);

        initRefreshLayout();
        swipeLayout.setRefreshing(true);
        stateful.showContent();
    }

    private void initRefreshLayout() {
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                isMore = false;
                index = 1;
                refresh(index);
            }
        });
    }
    private void refresh(int i) {
        index = 1;
        homeAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
////        mPresenter.getCookBooks(Constants.BASEURL_COOKBOOK, "秘制红烧肉", "json","", "10", 0, "efcc30370973714b39cbf36560142001");
        mPresenter.getSearchResultData(Constants.BASEURL_COOKBOOKS, "288305d30cae0", searchname, i, 20);
    }


    @Override
    public void doBusiness() {
        recyclerview_searchview.setHasFixedSize(true);
        recyclerview_searchview.setLayoutManager(new LinearLayoutManager(this));
        homeAdapter = new HomeAdapter(R.layout.fragment_task_item_view, mDataList);
        recyclerview_searchview.setAdapter(homeAdapter);

        homeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                isMore = true;
//                homeAdapter.setEnableLoadMore(true);
                loadmore(++index);
            }
        },recyclerview_searchview);

        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CookMenuSearchBean.Lists lists = mDataList.get(position);
                CookBookActivity.start(SearchResultActivity.this,lists);
            }
        });
    }

    private void loadmore(int i) {
//        mPresenter.getCookBooks(Constants.BASEURL_COOKBOOK, "秘制红烧肉", "json", i*10+ "", "10", 0, "efcc30370973714b39cbf36560142001");
        mPresenter.getSearchResultData(Constants.BASEURL_COOKBOOKS, "288305d30cae0", searchname, i, 20);
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
    public void getSearchResultData(CookMenuSearchBean bean) {
        LogUtils.e("getSearchResultData---" + bean.getResult().getList().get(0).getName());
        if (index == 1) {
            mDataList.clear();
        }
        if (bean.getErrCode() == 200) {
            List<CookMenuSearchBean.Lists> list = bean.getResult().getList();
            mDataList.addAll(bean.getResult().getList());
            if (homeAdapter != null) {
                homeAdapter.setNewData(mDataList);
            }
            boolean isenable = (index + 1) * 20 < mDataList.size();
            int size = bean.getResult().getList().size();
            totalNum = bean.getResult().getTotal();
            if (isMore) {
                if ((index + 1) * 20 >= totalNum) {
                    if (size < 20) {
                        homeAdapter.loadMoreEnd(isenable);
                    } else {
                        homeAdapter.loadMoreComplete();
                    }
                } else {
                    homeAdapter.setEnableLoadMore(true);
                }
            } else {
                homeAdapter.setEnableLoadMore(true);
                if (index == 1 && totalNum <= 20) {
                    homeAdapter.loadMoreEnd(isenable);
                }
            }

        } else {
            stateful.showError(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refresh(index);
                }
            });
        }
        swipeLayout.setRefreshing(false);
    }
}
