package com.platform.util;

import com.platform.VO.HttpResult;

public class HttpResultUtil<T> {
    public static <T> HttpResult<T> success(T data) {
        HttpResult<T> result = new HttpResult<>();
        result.setCode(200);
        result.setData(data);
        return result;
    }

    public static <T> HttpResult<T> success(String msg) {
        HttpResult<T> result = new HttpResult<>();
        result.setCode(200);
        result.setMsg(msg);
        return result;
    }

    public static <T> HttpResult<T> error(Integer code, String msg) {
        HttpResult<T> result = new HttpResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}