package com.englishwise.naaticclenglishwise.storage;

public class User_login {
    int loginid;
    private String id;
    private String fullname;
    private String phone;
    private String email;
    private String profileImage;
    private String language;


    public User_login(int loginid,String id, String fullname, String phone, String email, String profileImage, String language) {
        this.loginid = loginid;
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.profileImage = profileImage;
        this.language = language;
    }

    public int getLoginid() {
        return loginid;
    }

    public String getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getLanguage() {
        return language;
    }
}


