package com.example.yunwen.testdemo1022.ui.Cook.Fragment.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.network.bean.MethodBean;
import com.example.yunwen.testdemo1022.utils.ImageUtil;

import java.util.List;

public class CookDetailMethodAdapter extends BaseQuickAdapter<MethodBean ,BaseViewHolder> {


    public CookDetailMethodAdapter(int layoutResId, @Nullable List<MethodBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MethodBean item) {
        helper.setText(R.id.text_content, item.getStep());
        LogUtils.e(item.getImg());
        if (item.getImg() == null) {
            helper.getView(R.id.imgv_step).setVisibility(View.GONE);
        } else {
            ImageUtil.showImg(mContext,item.getImg(),R.drawable.load_error,R.drawable.load_error,(ImageView) helper.getView(R.id.imgv_step),1f);
        }

    }
}
