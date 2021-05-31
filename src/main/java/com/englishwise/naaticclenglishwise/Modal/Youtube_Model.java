package com.englishwise.naaticclenglishwise.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Youtube_Model implements Serializable {

    public String profileVideoId;
    public String videoName;
    public String title;
    public String thumbnailImage;
    public String videoUrl;
    public String key;


    public Youtube_Model(String profileVideoId, String videoName, String title, String thumbnailImage, String videoUrl, String key) {
        this.profileVideoId = profileVideoId;
        this.videoName = videoName;
        this.title = title;
        this.thumbnailImage = thumbnailImage;
        this.videoUrl = videoUrl;
        this.key = key;
    }

    public Youtube_Model(String videoUrl, String title, String key) {
        this.videoUrl = videoUrl;
        this.title = title;
        this.key = key;
    }

}
