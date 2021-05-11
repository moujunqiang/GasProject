package com.android.gasproject.http.response;


import java.io.Serializable;

public class UserBean implements Serializable {
    private int id;
    private String account;
    private String pwd;
    private String nickname;
    private String usericon;
    private String money;
    private String gasstatus;

    public String getGasstatus() {
        return gasstatus;
    }

    public void setGasstatus(String gasstatus) {
        this.gasstatus = gasstatus;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsericon() {
        return usericon;
    }

    public void setUsericon(String usericon) {
        this.usericon = usericon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }



    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +

                '}';
    }
}
