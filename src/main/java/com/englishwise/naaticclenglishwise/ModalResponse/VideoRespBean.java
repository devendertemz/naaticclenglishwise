package com.englishwise.naaticclenglishwise.ModalResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class VideoRespBean {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Datum[] results;

    public Datum[] getResults() {
        return results;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public class Datum {

        @SerializedName("profile_video_id")
        @Expose
        private String profileVideoId;
        @SerializedName("video_name")
        @Expose
        private String videoName;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("thumbnail_image")
        @Expose
        private String thumbnailImage;
        @SerializedName("video_url")
        @Expose
        private String videoUrl;
        @SerializedName("satus")
        @Expose
        private String satus;

        public String getProfileVideoId() {
            return profileVideoId;
        }

        public void setProfileVideoId(String profileVideoId) {
            this.profileVideoId = profileVideoId;
        }

        public String getVideoName() {
            return videoName;
        }

        public void setVideoName(String videoName) {
            this.videoName = videoName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumbnailImage() {
            return thumbnailImage;
        }

        public void setThumbnailImage(String thumbnailImage) {
            this.thumbnailImage = thumbnailImage;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getSatus() {
            return satus;
        }

        public void setSatus(String satus) {
            this.satus = satus;
        }

    }
}