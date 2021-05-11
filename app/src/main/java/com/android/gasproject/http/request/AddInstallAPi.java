package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**

 *    desc   : 添报装数据
 */
public class AddInstallAPi implements IRequestApi {

    @Override
    public String getApi() {
        return "/install/addInstall";
    }
    private String uname;
    private String uphone;
    private String uaddress;
    private String info;

    public AddInstallAPi setUname(String uname) {
        this.uname = uname;
        return this;
    }

    public AddInstallAPi setUphone(String uphone) {
        this.uphone = uphone;
        return this;
    }

    public AddInstallAPi setUaddress(String uaddress) {
        this.uaddress = uaddress;
        return this;
    }

    public AddInstallAPi setInfo(String info) {
        this.info = info;
        return this;
    }
}