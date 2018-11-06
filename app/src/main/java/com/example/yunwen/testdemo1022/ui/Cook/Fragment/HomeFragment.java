package com.example.yunwen.testdemo1022.ui.Cook.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.base.BaseFragment;
import com.example.yunwen.testdemo1022.constants.Constants;
import com.example.yunwen.testdemo1022.mvp.fragment.HomeFragmentPresenter;
import com.example.yunwen.testdemo1022.mvp.fragment.IHomeFragmentView;
import com.example.yunwen.testdemo1022.network.bean.CookCategoryBean;
import com.example.yunwen.testdemo1022.network.bean.CookMenuSearchBean;
import com.example.yunwen.testdemo1022.network.bean.CookSearchBean;
import com.example.yunwen.testdemo1022.ui.Cook.Fragment.adapter.HomeAdapter;
import com.example.yunwen.testdemo1022.ui.Cook.Fragment.adapter.HomeItemAdapter;
import com.example.yunwen.testdemo1022.ui.Cook.activity.CookBookActivity;
import com.example.yunwen.testdemo1022.ui.Cook.activity.CookChannelActivity;
import com.example.yunwen.testdemo1022.ui.widget.NoScrollViewPager;
import com.gturedi.views.StatefulLayout;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<HomeFragmentPresenter> implements IHomeFragmentView {

    private RecyclerView recyclerview_home;
    private BaseQuickAdapter homeAdapter;
    private SwipeRefreshLayout swipeLayout;
    private int totalNum = 0;
    private boolean isMore = false;
    private int index = 1;
    private StatefulLayout stateful;
    private TabLayout tablayout;
    private NoScrollViewPager view_pager;
    private RecyclerView recyclerview_title;
    private HomeItemAdapter homeItemAdapter;
    private String ctgid;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.i("onAttach");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtils.i("onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.i("onActivityCreated");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.i("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.i("onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.i("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.i("onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.i("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.i("onDetach");
    }

    private List<CookMenuSearchBean.Lists> mDataList = new ArrayList<>();
    private List<CookCategoryBean.Result.Childs.Childss> mCategoryList = new ArrayList();


    @Override
    protected void initPresenter() {
        mPresenter = new HomeFragmentPresenter();
    }


    @Override
    public void initData(@Nullable Bundle bundle) {
//        mPresenter.getCookBooks(Constants.BASEURL_COOKBOOK, "秘制红烧肉", "json", "", "10", 0, "efcc30370973714b39cbf36560142001");
        mPresenter.getCookCategorys(Constants.BASEURL_COOKBOOKS, "288305d30cae0");
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

        recyclerview_home = contentView.findViewById(R.id.recyclerview_home);
        swipeLayout = contentView.findViewById(R.id.swipeLayout);
        stateful = contentView.findViewById(R.id.stateful);

        recyclerview_title = contentView.findViewById(R.id.recyclerview_title);
        ImageView imgv_add = contentView.findViewById(R.id.imgv_add);

        initRefreshLayout();
        swipeLayout.setRefreshing(true);
        stateful.showContent();
        imgv_add.setOnClickListener(this);

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


    @Override
    public void doBusiness() {
        recyclerview_title.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerview_title.setLayoutManager(linearLayoutManager);
        homeItemAdapter = new HomeItemAdapter(R.layout.fragment_title_item_view, mCategoryList);
        recyclerview_title.setAdapter(homeItemAdapter);
        recyclerview_home.setHasFixedSize(true);
        recyclerview_home.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(R.layout.fragment_task_item_view, mDataList);
        recyclerview_home.setAdapter(homeAdapter);

        homeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                isMore = true;
//                homeAdapter.setEnableLoadMore(true);
                loadmore(++index);
            }
        },recyclerview_home);

        homeItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                view.findViewById(R.id.view).setBackgroundColor(getActivity().getResources().getColor(R.color.firstColor));
                mCategoryList.get(position).setSelect(true);
                homeItemAdapter.notifyDataSetChanged();
                ToastUtils.showShort(position + "");
                ctgid = mCategoryList.get(position).getCategoryinfo().getCtgid();
                setFirstPageData(ctgid);
            }
        });

        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CookMenuSearchBean.Lists lists = mDataList.get(position);
                CookBookActivity.start(getContext(),lists);
            }
        });
    }

    /**
     *  分类数据更新，分页更新
     *  i 指当前页数
     */
    private void refresh(int i) {
        index = 0;
        homeAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
////        mPresenter.getCookBooks(Constants.BASEURL_COOKBOOK, "秘制红烧肉", "json","", "10", 0, "efcc30370973714b39cbf36560142001");
        mPresenter.getCookMenuSearch(Constants.BASEURL_COOKBOOKS, "288305d30cae0", ctgid, i, 20);
    }

    /**
     * 加载更多数据
     * @param i 当前页
     */
    private void loadmore(int i) {
//        mPresenter.getCookBooks(Constants.BASEURL_COOKBOOK, "秘制红烧肉", "json", i*10+ "", "10", 0, "efcc30370973714b39cbf36560142001");
        mPresenter.getCookMenuSearch(Constants.BASEURL_COOKBOOKS, "288305d30cae0", ctgid, i, 20);
    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                break;
            case R.id.imgv_add:
                CookChannelActivity.start(getContext());
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search:
                break;
            case R.id.imgv_add:
                CookChannelActivity.start(getContext());
                break;
        }
    }


    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onCompleted() {
        LogUtils.e("onCompleted--" );
    }

    @Override
    public void onHomeData(CookSearchBean bean) {

    }

    @Override
    public void onCategoryData(CookCategoryBean bean) {
        LogUtils.e("onCategoryData---" + bean.getResult().getChilds().get(0).getCategoryinfo().getName() + "");
        List<CookCategoryBean.Result.Childs.Childss> childs = bean.getResult().getChilds().get(0).getChilds();
        ctgid = childs.get(0).getCategoryinfo().getCtgid();
        for (int i = 0; i < childs.size(); i++) {
            if (i == 0) {
                childs.get(i).setSelect(true);
            } else {
                childs.get(i).setSelect(false);
            }
        }
        mCategoryList.addAll(childs);
        homeItemAdapter.setNewData(mCategoryList);
//        homeAdapter.setNewData(mCategoryList);
        swipeLayout.setRefreshing(false);
        //设置第一页显示的菜谱数据
        setFirstPageData(ctgid);
    }

    private void setFirstPageData(String ctgid) {
        index = 0;
        mPresenter.getCookMenuSearch(Constants.BASEURL_COOKBOOKS, "288305d30cae0", ctgid, 1, 20);
    }

    @Override
    public void onMenuSearchData(CookMenuSearchBean bean) {
        LogUtils.e("onMenuSearchData---" + bean.getResult().getList().get(0).getName());
        if (index == 0) {
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
                if (index == 0 && totalNum <= 20) {
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
