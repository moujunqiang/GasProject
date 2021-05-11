package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**

 *    desc   : 更改用户信息
 */
public class ChangeInfoApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/user/update";
    }
    private String userId;
    private String name;
    private String usericon;

    public ChangeInfoApi setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public ChangeInfoApi setName(String name) {
        this.name = name;
        return this;

    }

    public ChangeInfoApi setUsericon(String usericon) {
        this.usericon = usericon;
        return this;

    }
}