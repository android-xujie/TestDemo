package com.example.yunwen.testdemo1022.ui.Cook.Fragment.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.network.bean.CookMenuSearchBean;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<CookMenuSearchBean.Lists, BaseViewHolder> {

    public HomeAdapter(int layoutResId, List<CookMenuSearchBean.Lists> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, CookMenuSearchBean.Lists item) {
        helper.setText(R.id.tv_title, item.getName());
//        helper.setText(R.id.tv_tags, item.getCtgtitles());
//        helper.setText(R.id.tv_ingredients,item.getMenuid() );
//        helper.setText(R.id.tv_burden, item.getCtgtitles());
        Glide.with(mContext)
                .load(item.getThumbnail())
                .into((ImageView) helper.getView(R.id.iv_pictrue));
    }
}
