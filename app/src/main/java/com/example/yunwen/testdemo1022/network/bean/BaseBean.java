package com.example.yunwen.testdemo1022.network.bean;

public class BaseBean {
    private int error_code;
    private String reason;

    public int getErrCode() {
        return error_code;
    }

    public void setErrCode(int errCode) {
        this.error_code = errCode;
    }

    public String getErrMess() {
        return reason;
    }

    public void setErrMess(String errMess) {
        this.reason = errMess;
    }
}
