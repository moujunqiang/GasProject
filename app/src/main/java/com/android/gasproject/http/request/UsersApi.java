package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * desc   : 获取所有得用户
 */
public class UsersApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/user/queryAllUser";
    }
}