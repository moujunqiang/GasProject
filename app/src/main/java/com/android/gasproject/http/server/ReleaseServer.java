package com.android.gasproject.http.server;

import com.android.gasproject.http.model.UrlContant;
import com.hjq.http.config.IRequestServer;
import com.hjq.http.config.IRequestType;
import com.hjq.http.model.BodyType;

/**
 * desc   : 正式环境
 */
public class ReleaseServer implements IRequestServer {

    @Override
    public String getHost() {
        return UrlContant.BASE_URL;
    }

    @Override
    public String getPath() {
        return "";
    }


}