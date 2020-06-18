package com.example.codemail.stationary_model;

public class CategoryModel {
    public String imglink, imgname;

    public CategoryModel(String imglink, String imgname) {
        this.imglink = imglink;
        this.imgname = imgname;
    }

    public String getimgname() {
        return imgname;
    }

    public void setimgname(String imgname) {
        this.imgname = imgname;
    }

    public String getimglink() {
        return imglink;
    }

    public void setimglink(String imglink) {
        this.imglink = imglink;
    }

    public CategoryModel() {
    }
}
