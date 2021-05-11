package com.android.gasproject.http.response;


import java.io.Serializable;

public class NotifyBean implements Serializable {



    private int id;
    private String ntitle;
    private String ncontent;
    private String nimage;
    private String ctime;

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getNcontent() {
        return ncontent;
    }

    public void setNcontent(String ncontent) {
        this.ncontent = ncontent;
    }

    public String getNimage() {
        return nimage;
    }

    public void setNimage(String nimage) {
        this.nimage = nimage;
    }
}
