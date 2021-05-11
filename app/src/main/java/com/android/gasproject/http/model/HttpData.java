package com.android.gasproject.http.model;

/**
 * desc   : 统一接口数据结构
 */
public class HttpData<T> {


    /**
     * 接口请求返回状态
     */
    private boolean success;
    private String error;
    /**
     * 数据
     */
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}