package com.bkw.study.data.home;

import java.io.Serializable;

public class BannerVO implements Serializable {
    private static final long serialVersionUID = -8946969270725252287L;

    public BannerVO() {
    }

    /**
     * desc : 享学~
     * id : 29
     * imagePath : https://wanandroid.com/blogimgs/6f9c0f25-e02d-48a5-bbfa-ed199416009a.png
     * isVisible : 1
     * order : 0
     * title : 产品上线，突发bug…
     * type : 0
     * url : https://mp.weixin.qq.com/s/TcF4w0Bz-k5dZdPKq3HIGA
     */

    private String desc;
    private int id;
    private String imagePath;
    private int isVisible;
    private int order;
    private String title;
    private int type;
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
