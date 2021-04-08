package com.boomer;

public class NotificationHelperClass {

    String img, title, desc;

    public NotificationHelperClass(String img, String title, String desc) {
        this.img = img;
        this.title = title;
        this.desc = desc;
    }

    public String getImage() {
        return img;
    }

    public void setImage(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String nDesc) {
        this.desc = nDesc;
    }
}
