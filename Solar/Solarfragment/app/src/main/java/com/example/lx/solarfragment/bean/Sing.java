package com.example.lx.solarfragment.bean;

public class Sing {
    private int singid;
    private String singurl;
    private String singname;

    public Sing() {
    }

    @Override
    public String toString() {
        return "Sing{" +
                "singname='" + singname + '\'' +
                '}';
    }

    public Sing(int singid, String singurl, String singname) {
        this.singid = singid;
        this.singurl = singurl;
        this.singname = singname;
    }

    public int getSingid() {
        return singid;
    }

    public void setSingid(int singid) {
        this.singid = singid;
    }

    public String getSingurl() {
        return singurl;
    }

    public void setSingurl(String singurl) {
        this.singurl = singurl;
    }

    public String getSingname() {
        return singname;
    }

    public void setSingname(String singname) {
        this.singname = singname;
    }
}
