package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**

 *    desc   : 更改用户燃气 状态
 */
public class ChangeUserStatusApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/user/changeStatus";
    }
    private String status;
    private String uid;

    public ChangeUserStatusApi setStatus(String status) {
        this.status = status;
        return this;

    }

    public ChangeUserStatusApi setUid(String uid) {
        this.uid = uid;
        return this;

    }
}