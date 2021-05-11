package com.android.gasproject.http.response;

public class History {
    private int id;
    private String uid;
    private String money;
    private String ctime;
    private UserBean user;
    public History() {
    }

    public History(Integer id, String uid, String money, String ctime) {
        this.id = id;
        this.uid = uid;
        this.money = money;
        this.ctime = ctime;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
