package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**

 *    desc   : 登录
 */
public class RegisterAPi implements IRequestApi {

    @Override
    public String getApi() {
        return "/user/register";
    }
    private String account;
    private String pwd;


    public RegisterAPi setAccount(String account) {
        this.account = account;
        return this;
    }

    public RegisterAPi setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }
}