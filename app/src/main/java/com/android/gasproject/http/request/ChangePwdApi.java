package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**

 *    desc   : 更改用户密码
 */
public class ChangePwdApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/user/changePwd";
    }
    private String pwd;
    private String uid;

    public ChangePwdApi setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    public ChangePwdApi setUid(String uid) {
        this.uid = uid;
        return this;
    }
}