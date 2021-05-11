package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**
 *    desc   : 根据id删除通知
 */
public class DeleteNotifyApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/notify/deleteNotify";
    }
    private String id;

    public DeleteNotifyApi setId(String id) {
        this.id = id;
        return this;
    }
}