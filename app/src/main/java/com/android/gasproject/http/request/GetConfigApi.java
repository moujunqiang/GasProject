package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**

 *    desc   : 获取配置
 */
public class GetConfigApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/config/getConfig";
    }
}