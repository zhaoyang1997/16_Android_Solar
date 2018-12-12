package com.example.lx.solarfragment.bean;

import android.graphics.Bitmap;

public class Picture {
    private Bitmap imageurl;
    private int userid;
    private int imageid;

    @Override
    public String toString() {
        return "Picture{" +
                "imageurl=" + imageurl +
                ", userid=" + userid +
                ", imageid=" + imageid +
                '}';
    }

    public Picture() {
    }

    public Picture(Bitmap imageurl, int userid, int imageid) {
        this.imageurl = imageurl;
        this.userid = userid;
        this.imageid = imageid;
    }

    public Bitmap getImageurl() {
        return imageurl;
    }

    public void setImageurl(Bitmap imageurl) {
        this.imageurl = imageurl;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

}
