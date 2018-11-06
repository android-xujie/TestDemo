package com.example.yunwen.testdemo1022.frames.data;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

/**
 * BaseDaoFactory： 用来创建数据库和初始化数据库
 * IBaseDao： 增删改查方法接口
 * BaseDao： 实现增删改查方法的抽象模板类(子类方法UserDao增删改查方法在该类实现)
 * Bean： 使用注解方式来定义表中相关字段名和类型，如UserBean
 * ConcreteDao： 创建表，定相关义字段及其长度，（UserDao）和UserBean中的字段对应
 * Client： 调用类，如Activity
 *
 */

public class BaseDaoFactory {
    private static BaseDaoFactory instance = new BaseDaoFactory();
    private String sqliteDetabasePath;
    private SQLiteDatabase sqLiteDatabase;

    public BaseDaoFactory() {
        sqliteDetabasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/teacher.db";
        openDatabase();
    }

    public static BaseDaoFactory getInstance() {
        return instance;
    }

    public synchronized <T extends BaseDao<M>, M> T getDataHelper(Class<T> clazz, Class<M> entityClass) {
        BaseDao baseDao = null;
        try {
            baseDao = clazz.newInstance();
            baseDao.init(entityClass, sqLiteDatabase);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return (T) baseDao;
    }

    /**
     * targetSdkVersion 大于22时 要申请存储读写权限
     * 打开数据库操作
     */
    private void openDatabase() {
//        PermissionUtils.permission("")
        this.sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqliteDetabasePath, null);
    }
}
