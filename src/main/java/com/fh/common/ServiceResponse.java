package com.fh.common;

public class ServiceResponse {
    private Integer code;
    private String msg;
    private Object data;

    public static ServiceResponse success(Object data){
        return new ServiceResponse(ServiceEnum.success.getCode(), ServiceEnum.success.getMsg(),data);
    }

    public static ServiceResponse success(){
        return new ServiceResponse(200,"操作成功");
    }

    public static ServiceResponse error(String msg){
        return new ServiceResponse(ServiceEnum.error.getCode(),msg);
    }

    public static ServiceResponse error(){
        return new ServiceResponse(ServiceEnum.error.getCode(), ServiceEnum.error.getMsg());
    }
    public static ServiceResponse login_error(){
        return new ServiceResponse(ServiceEnum.LOGIN_ERROE.getCode(), ServiceEnum.LOGIN_ERROE.getMsg());
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ServiceResponse() {
    }

    public ServiceResponse(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ServiceResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
