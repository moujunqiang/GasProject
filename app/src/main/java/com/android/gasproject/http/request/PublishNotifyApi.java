package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**

 *    desc   : 发布通知
 */
public class PublishNotifyApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/notify/insertNotify";
    }
    private String ntitle;
    private String ncontent;


    public PublishNotifyApi setNtitle(String ntitle) {
        this.ntitle = ntitle;
        return this;
    }


    public PublishNotifyApi setNcontent(String ncontent) {
        this.ncontent = ncontent;
        return this;
    }
}