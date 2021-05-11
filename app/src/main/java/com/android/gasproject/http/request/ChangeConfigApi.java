package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**
 * desc   : 获取配置
 */
public class ChangeConfigApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/config/updateConfig";
    }

    private String rules;

    public ChangeConfigApi setRules(String rules) {
        this.rules = rules;
        return this;
    }
}