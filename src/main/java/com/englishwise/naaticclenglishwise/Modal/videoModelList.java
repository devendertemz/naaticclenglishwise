package com.englishwise.naaticclenglishwise.Modal;

public class videoModelList {

    String Title, Title2, Date;

    int ScreenImg;


    public videoModelList(String date,String title, String title2, int screenImg) {
        Title = title;
        Title2 = title2;
        Date = date;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getScreenImg() {
        return ScreenImg;
    }

    public void setScreenImg(int screenImg) {
        ScreenImg = screenImg;
    }
}
