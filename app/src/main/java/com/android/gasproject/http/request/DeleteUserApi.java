package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**
 *    desc   : 根据id删除用户信息
 */
public class DeleteUserApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/user/deleteUser";
    }
    private String userId;

    public DeleteUserApi setUserId(String userId) {
        this.userId = userId;
        return this;
    }
}