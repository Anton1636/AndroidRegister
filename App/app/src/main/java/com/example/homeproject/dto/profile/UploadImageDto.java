package com.example.homeproject.dto.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UploadImageDto {
    @SerializedName("image")
    @Expose
    private String image;

    public UploadImageDto() {
    }

    public UploadImageDto(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}