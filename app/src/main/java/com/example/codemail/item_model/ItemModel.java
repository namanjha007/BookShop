package com.example.codemail.item_model;

public class ItemModel {
    public String imglink, brand, itemname, costprice, sellprice, type, colour;

    public String getimglink() {
        return imglink;
    }

    public void setimglink(String imglink) {
        this.imglink = imglink;
    }

    public String getBrand() {
        return brand;
    }

    public void setbrand(String brand) {
        this.brand = brand;
    }

    public String getitemname() {
        return itemname;
    }

    public void setitemname(String itemname) {
        this.itemname = itemname;
    }

    public String getcostprice() {
        return costprice;
    }

    public void setcostprice(String costprice) {
        this.costprice = costprice;
    }

    public String getsellprice() {
        return sellprice;
    }

    public void setsellprice(String sellprice) {
        this.sellprice = sellprice;
    }

    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }

    public String getcolour() {
        return colour;
    }

    public void setcolour(String colour) {
        this.colour = colour;
    }

    public ItemModel() {
    }

    public ItemModel(String imglink, String brand, String itemname, String costprice, String sellprice, String type, String colour) {
        this.imglink = imglink;
        this.brand = brand;
        this.itemname = itemname;
        this.costprice = costprice;
        this.sellprice = sellprice;
        this.type = type;
        this.colour = colour;
    }
}
