package com.example.yunwen.testdemo1022.frames.data;

public class UserDao extends BaseDao {

    @Override
    protected String createTable() {
        return "create table if not exists tb_user(userId int,name varchar(20),password varchar(20))";
    }
}
