package com.example.yunwen.testdemo1022.ui.Cook.Fragment.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.network.bean.MethodBean;
import com.example.yunwen.testdemo1022.ui.Cook.entity.MultipleItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.ArrayList;
import java.util.List;

public class CookDetailAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    private ArrayList<String> ingredientsDatas;
    private ArrayList<MethodBean> cookRecipeMethods;

    public CookDetailAdapter(Context context, List data) {
        super(data);
        addItemType(MultipleItem.Cook_Detail_Item_Type_CookMan, R.layout.item_cookdetail_top_view);
        addItemType(MultipleItem.Cook_Detail_Item_Type_Header, R.layout.item_cookdetail_head_view);
        addItemType(MultipleItem.Cook_Detail_Item_Type_Step, R.layout.item_cookdetail_step_view);

    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.Cook_Detail_Item_Type_CookMan:
                helper.setText(R.id.text_sumary, item.getContent());
                helper.setText(R.id.text_title, item.getTitle());
                break;
            case MultipleItem.Cook_Detail_Item_Type_Header:
                String ingredients = item.getContent();
                if (null == ingredients || TextUtils.isEmpty(ingredients)) {
                    ingredientsDatas = new ArrayList<>();
                } else {
                    ingredients = ingredients.replace("\\", "");
                    ingredientsDatas = new Gson().fromJson(ingredients, new TypeToken<ArrayList<String>>() {
                    }.getType());
                }
                if (ingredientsDatas.size() < 1) {
                    helper.getView(R.id.relative_view1).setVisibility(View.GONE);
                    helper.getView(R.id.relative_view2).setVisibility(View.GONE);
                    helper.getView(R.id.relative_view3).setVisibility(View.GONE);
                    helper.getView(R.id.text_ingredients).setVisibility(View.GONE);
                } else if (ingredientsDatas.size() < 2) {
                    helper.getView(R.id.relative_view1).setVisibility(View.VISIBLE);
                    helper.getView(R.id.relative_view2).setVisibility(View.GONE);
                    helper.getView(R.id.relative_view3).setVisibility(View.GONE);

                    ((TextView) helper.getView(R.id.text_ingredients_content1)).setText(ingredientsDatas.get(0));
                } else if (ingredientsDatas.size() < 3) {
                    helper.getView(R.id.relative_view1).setVisibility(View.VISIBLE);
                    helper.getView(R.id.relative_view2).setVisibility(View.VISIBLE);
                    helper.getView(R.id.relative_view3).setVisibility(View.GONE);

                    ((TextView) helper.getView(R.id.text_ingredients_content1)).setText(ingredientsDatas.get(0));
                    ((TextView) helper.getView(R.id.text_ingredients_content2)).setText(ingredientsDatas.get(1));
                } else {
                    helper.getView(R.id.relative_view1).setVisibility(View.VISIBLE);
                    helper.getView(R.id.relative_view2).setVisibility(View.VISIBLE);
                    helper.getView(R.id.relative_view3).setVisibility(View.VISIBLE);

                    ((TextView) helper.getView(R.id.text_ingredients_content1)).setText(ingredientsDatas.get(0));
                    ((TextView) helper.getView(R.id.text_ingredients_content2)).setText(ingredientsDatas.get(1));
                    ((TextView) helper.getView(R.id.text_ingredients_content3)).setText(ingredientsDatas.get(2));
                }
                LogUtils.e(ingredientsDatas);
                break;
            case MultipleItem.Cook_Detail_Item_Type_Step:
                String method = item.getContent();
                if(null == method || TextUtils.isEmpty(method)){
                    cookRecipeMethods = new ArrayList<>();
                }
                else {
                    method = method.replace("\\", "");
                    cookRecipeMethods = new Gson().fromJson(method, new TypeToken<ArrayList<MethodBean>>() {}.getType());
                }
                RecyclerView recyclerview_method = helper.getView(R.id.recyclerview_method);
                recyclerview_method.setLayoutManager(new LinearLayoutManager(item.getContext()));
                CookDetailMethodAdapter cookDetailMethodAdapter = new CookDetailMethodAdapter(R.layout.item_cookdetail_step_method_view, cookRecipeMethods);
                recyclerview_method.setAdapter(cookDetailMethodAdapter);
                LogUtils.e(cookRecipeMethods.get(0).getStep());
                break;
        }
    }
}
