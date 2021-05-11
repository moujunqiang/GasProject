package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**
 *    desc   : 根据状态获取安装
 */
public class GetInstallApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/install/queryInstallByStatus";
    }
    private String status;

    public GetInstallApi setStatus(String status) {
        this.status = status;
        return this;
    }
}