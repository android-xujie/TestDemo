package com.example.yunwen.testdemo1022.ui.bomb.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.yunwen.testdemo1022.R;
import com.example.yunwen.testdemo1022.base.BaseBackActivity;
import com.example.yunwen.testdemo1022.ui.bomb.bean.Person;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class PersonActivity extends BaseBackActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, PersonActivity.class);
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
        return R.layout.activity_person;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        Button bt_adddata = findViewById(R.id.bt_adddata);
        Button bt_getdata = findViewById(R.id.bt_getdata);
        Button bt_deletedata = findViewById(R.id.bt_deletedata);
        bt_adddata.setOnClickListener(this);
        bt_getdata.setOnClickListener(this);
        bt_deletedata.setOnClickListener(this);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.bt_adddata:
                addData();
                break;
            case R.id.bt_getdata:
                getData();
                break;
            case R.id.bt_deletedata:
                deleteData();
                break;
        }
    }

    private void deleteData() {
        final Person p2 = new Person();
        p2.setObjectId("1d6cbdbe98");
        p2.delete(new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    ToastUtils.showShort("删除成功:"+ p2.getUpdatedAt());
                }else{
                    ToastUtils.showShort("删除失败：" + e.getMessage());
                }
            }

        });
    }

    private void getData() {
        //查找Person表里面id为6b6c11c537的数据
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject("1d6cbdbe98", new QueryListener<Person>() {
            @Override
            public void done(Person object,BmobException e) {
                if(e==null){
                    LogUtils.e("getData---" + object.getName() + "---" + object.getAddress());
                    ToastUtils.showShort("查询成功");
                }else{
                    LogUtils.e("getData---" + e.getMessage());
                    ToastUtils.showShort("查询失败：" + e.getMessage());
                }
            }
        });
    }

    private void addData() {
        Person p2 = new Person();
        p2.setName("lucky");
        p2.setAddress("北京海淀");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    ToastUtils.showShort("添加数据成功，返回objectId为："+objectId);
                }else{
                    ToastUtils.showShort("创建数据失败：" + e.getMessage());
                }
            }
        });
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onCompleted() {

    }
}
