package com.android.gasproject.http.model;

/**
 * desc   : 统一接口数据结构
 */
public class HttpData<T> {


    /**
     * 接口请求返回状态
     */
    private int rsp_ret;
    /**
     * 数据
     */
    private T data;

    public int getRsp_ret() {
        return rsp_ret;
    }

    public void setRsp_ret(int rsp_ret) {
        this.rsp_ret = rsp_ret;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}