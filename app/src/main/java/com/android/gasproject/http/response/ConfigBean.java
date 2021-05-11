package com.android.gasproject.http.response;


public class ConfigBean {



    private int id;
    private String rules;

    public ConfigBean() {
    }

    public ConfigBean(int id, String rules) {
        this.id = id;
        this.rules = rules;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }
}
