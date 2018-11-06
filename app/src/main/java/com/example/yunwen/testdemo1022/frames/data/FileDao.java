package com.example.yunwen.testdemo1022.frames.data;

public class FileDao extends BaseDao {
    @Override
    protected String createTable() {
        return "create table if not exists tb_file(time varchar(20),path varchar(20),description varchar(20))";
    }

}