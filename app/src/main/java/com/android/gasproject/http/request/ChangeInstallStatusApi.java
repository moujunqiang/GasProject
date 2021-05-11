package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**

 *    desc   : 更改报装 状态
 */
public class ChangeInstallStatusApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/install/updateStatus";
    }
    private String status;
    private String id;

    public ChangeInstallStatusApi setStatus(String status) {
        this.status = status;
        return this;

    }

    public ChangeInstallStatusApi setId(String id) {
        this.id = id;
        return this;
    }
}