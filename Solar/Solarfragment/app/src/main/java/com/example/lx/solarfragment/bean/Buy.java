package com.example.lx.solarfragment.bean;

public class Buy {

    private int userId;
    private int ImagesId;
    private String imageUrl;

    public Buy(){}

    public Buy(int userId,int ImageId){
        this.userId = userId;
        this.ImagesId = ImageId;
    }

    public int getImagesId() {
        return ImagesId;
    }

    public void setImagesId(int imagesId) {
        ImagesId = imagesId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
