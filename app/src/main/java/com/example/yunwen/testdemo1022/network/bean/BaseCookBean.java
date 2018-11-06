package com.example.yunwen.testdemo1022.network.bean;

public class BaseCookBean {
    private int retCode;
    private String msg;

    public int getErrCode() {
        return retCode;
    }

    public void setErrCode(int errCode) {
        this.retCode = errCode;
    }

    public String getErrMess() {
        return msg;
    }

    public void setErrMess(String errMess) {
        this.msg = errMess;
    }
}
