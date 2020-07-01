package com.show.qixin.api.common.bean;

import lombok.Data;

@Data
public class ResponseBean<T> {

    /** 200:操作成功  -1：操作失败**/

    // http 状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    private T data;

    public ResponseBean(){}

    public ResponseBean(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseBean error(String message) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setMsg(message);
        responseBean.setCode(-1);
        return responseBean;
    }

    public static ResponseBean error(int code,String message) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setMsg(message);
        responseBean.setCode(code);
        return responseBean;
    }

    public static<T> ResponseBean success(T data) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setData(data);
        responseBean.setCode(200);
        responseBean.setMsg("操作成功");
        return responseBean;
    }

    public static ResponseBean success(String message) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setData(null);
        responseBean.setCode(200);
        responseBean.setMsg(message);
        return responseBean;
    }

    public static ResponseBean success() {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setData(null);
        responseBean.setCode(200);
        responseBean.setMsg("操作成功");
        return responseBean;
    }
}
