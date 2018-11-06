package com.example.yunwen.testdemo1022.frames.data;

public class User {

    /**
     * 数据字段支持注解和非注解
     * 非注解：
     *     public Integer userId;
     * 数据保存的字段名为 userId
     * 注解：
     *     @DbFiled("teacher_id")
     *     public Integer userId;
     *  数据保存的字段名为 teacher_id
     *
     *  要用Integer型 不要用int型，负责查询不到
     */
    public Integer userId;


    public String name;

    public String password;

    public User(Integer userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public User() {
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
