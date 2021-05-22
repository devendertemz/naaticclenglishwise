package com.englishwise.naaticclenglishwise.ModalResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BlogRespBean  {

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

        @SerializedName("blog_id")
        @Expose
        private String blogId;
        @SerializedName("Image")
        @Expose
        private String image;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("short_description")
        @Expose
        private String shortDescription;
        @SerializedName("long_description")
        @Expose
        private String longDescription;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_at")
        @Expose
        private Object createdAt;

        public String getBlogId() {
            return blogId;
        }

        public void setBlogId(String blogId) {
            this.blogId = blogId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getLongDescription() {
            return longDescription;
        }

        public void setLongDescription(String longDescription) {
            this.longDescription = longDescription;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Object createdAt) {
            this.createdAt = createdAt;
        }

    }
}