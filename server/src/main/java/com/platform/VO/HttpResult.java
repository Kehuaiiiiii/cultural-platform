package com.platform.VO;

public class HttpResult<T> {
    private Integer code; // 返回代码(200:成功)
    private String msg="";//返回提示信息
    private T data; // 返回的数据

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }



    public HttpResult() {
        super();
    }

    public HttpResult(Integer code, T data) {
        super();
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
