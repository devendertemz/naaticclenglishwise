package com.englishwise.naaticclenglishwise.Modal;

import android.graphics.drawable.Drawable;

public class videoModel {

    String Title, Title2, Description;

    int ScreenImg;

    public videoModel(String title2, String description, int screenImg) {
        Title2 = title2;
        Description = description;
        ScreenImg = screenImg;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTitle2() {
        return Title2;
    }

    public void setTitle2(String title2) {
        Title2 = title2;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getScreenImg() {
        return ScreenImg;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }
}
