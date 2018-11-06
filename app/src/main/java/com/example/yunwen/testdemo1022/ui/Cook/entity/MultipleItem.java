package com.example.yunwen.testdemo1022.ui.Cook.entity;


import android.content.Context;

public class MultipleItem implements com.chad.library.adapter.base.entity.MultiItemEntity {

    public static final int Cook_Detail_Item_Type_CookMan = 0;
    public final static int Cook_Detail_Item_Type_Header = 1;
    public final static int Cook_Detail_Item_Type_Step = 2;

    private Context context;

    private String title;

    private int itemType;

    private String content;

    public MultipleItem(int itemType, String content, Context context) {
        this.itemType = itemType;
        this.content = content;
        this.context = context;
    }

    public MultipleItem(int itemType,String title,String content,Context context) {
        this.itemType = itemType;
        this.title = title;
        this.content = content;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
