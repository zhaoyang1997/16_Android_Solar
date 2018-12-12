package com.example.lx.solarfragment.bean;

import android.graphics.Bitmap;

public class MyImg {
    private String imageUrl;
    private int id;
    private Bitmap bitmap;
    private String price;


    public MyImg(){}

    public MyImg(int id,Bitmap bitmap,String price){
        this.id = id;
        this.bitmap = bitmap;
        this.price = price;

    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public  String toString(){
        return "MyImg{" +
                "price='" + price + '\'' +
                ", id='" + id + '\''+
                '}';
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
