package com.applazada.Models;

public class Category {
    private String url_img;
    private String notify;
    private String cate_name;


    public Category() {
    }

    public Category(String url_img, String notify, String cate_name) {
        this.url_img = url_img;
        this.notify = notify;
        this.cate_name = cate_name;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }

    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }
}
