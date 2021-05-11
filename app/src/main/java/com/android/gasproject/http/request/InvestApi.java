package com.android.gasproject.http.request;

import com.hjq.http.config.IRequestApi;

/**

 *    desc   : 充值
 */
public class InvestApi implements IRequestApi {

    @Override
    public String getApi() {
        return "/user/updateMoney";
    }
    private String account;
    private String money;


    public InvestApi setAccount(String account) {
        this.account = account;
        return this;
    }

    public InvestApi setMoney(String money) {
        this.money = money;
        return this;

    }
}