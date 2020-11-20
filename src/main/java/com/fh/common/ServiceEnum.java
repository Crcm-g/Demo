package com.fh.common;

public enum ServiceEnum {
    success(200,"操作成功"),
    error(1001,"操作失败"),
    LOGIN_ERROE(1002,"登录失败")
    ;
    private Integer code;
    private String msg;

    ServiceEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
