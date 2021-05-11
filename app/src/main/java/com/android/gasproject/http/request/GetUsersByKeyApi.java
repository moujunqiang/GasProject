package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * desc   : 获取所有
 */
public class GetUsersByKeyApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/user/queryUserByKey";
    }

    String key;
    public GetUsersByKeyApi setKey(String key) {
        this.key = key;
        return this;
    }


}