package com.smss.net;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 张瑶
 * @Description: 将结果转换为封装后的对象
 * @date 2018/4/19 09:45
 */
public class RetResponse {

    private final static String SUCCESS = "success";

    public static <T> RetResult<T> makeOKRsp(String message) {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(message);
    }


    //正确的返回
    public static <T> RetResult<T> makeOKRsp(T data, String message) {
        return new RetResult<T>().setCode(RetCode.SUCCESS).setMsg(message).setData(data);
    }


    public static <T> RetResult<T> makeNullDataRsp(String message) {
        return new RetResult<T>().setCode(RetCode.FAIL).setMsg(message).setData((T) new JSONObject());
    }

    //挤下线
    public static <T> RetResult<T> reLoginRsp(T data,String message) {
        return new RetResult<T>().setCode(RetCode.RELOGIN).setMsg(message).setData(data);
    }


    public static <T> RetResult<T> makeErrRsp(String message) {
        return new RetResult<T>().setCode(RetCode.FAIL).setMsg(SUCCESS);
    }

    public static <T> RetResult<T> makeRsp(int code, String msg) {
        return new RetResult<T>().setCode(code).setMsg(msg);
    }

    public static <T> RetResult<T> makeRsp(int code, String msg, T data) {
        return new RetResult<T>().setCode(code).setMsg(msg).setData(data);
    }
}
