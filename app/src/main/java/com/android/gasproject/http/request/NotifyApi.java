package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**

 *    desc   : 充值历史记录
 */
public class NotifyApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/notify/getAllNotify";
    }



}