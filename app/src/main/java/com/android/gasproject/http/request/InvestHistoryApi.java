package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**

 *    desc   : 充值历史记录
 */
public class InvestHistoryApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/user/queryHistory";
    }
    private String uid;


    public InvestHistoryApi setUid(String uid) {
        this.uid = uid;
        return this;
    }
}