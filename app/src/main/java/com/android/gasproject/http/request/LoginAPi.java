package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**

 *    desc   : 登录
 */
public class LoginAPi implements IRequestApi {

    @Override
    public String getApi() {
        return "/user/login";
    }
    private String account;
    private String pwd;


    public LoginAPi setAccount(String account) {
        this.account = account;
        return this;
    }

    public LoginAPi setPwd(String pwd) {
        this.pwd = pwd;
        return this;

    }
}