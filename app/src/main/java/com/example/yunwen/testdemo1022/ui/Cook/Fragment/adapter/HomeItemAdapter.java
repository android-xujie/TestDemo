package com.example.yunwen.testdemo1022.ui.Cook.Fragment.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.network.bean.CookCategoryBean;

import java.util.List;

public class HomeItemAdapter extends BaseQuickAdapter<CookCategoryBean.Result.Childs.Childss, BaseViewHolder> {

    public HomeItemAdapter(int layoutResId, List<CookCategoryBean.Result.Childs.Childss> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final CookCategoryBean.Result.Childs.Childss item) {
        helper.setText(R.id.tv_item, item.getCategoryinfo().getName());
//        helper.getView(R.id.tv_item).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                item.setSelect(true);
//                notifyDataSetChanged();
//            }
//        });
        LogUtils.e("convert---" + helper.getAdapterPosition() + "---" + item.getSelect());
        if (item.getSelect()) {
            helper.getView(R.id.tv_item).setBackgroundColor(Color.RED);
            ((TextView)helper.getView(R.id.tv_item)).setTextColor(Color.WHITE);
            item.setSelect(false);
        } else {
            helper.getView(R.id.tv_item).setBackgroundColor(Color.WHITE);
            ((TextView)helper.getView(R.id.tv_item)).setTextColor(Color.BLACK);
        }
    }
}
