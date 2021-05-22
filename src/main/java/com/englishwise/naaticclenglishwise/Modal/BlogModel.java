package com.englishwise.naaticclenglishwise.Modal;

import java.io.Serializable;

public class BlogModel  implements Serializable {

    public String blogId;
    public String image;

    public String title;
    public String shortDescription;
    public String longDescription;

    public BlogModel(String blogId, String image, String title, String shortDescription, String longDescription) {
        this.blogId = blogId;
        this.image = image;
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }
}
