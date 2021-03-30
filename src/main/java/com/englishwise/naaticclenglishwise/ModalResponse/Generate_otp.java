package com.englishwise.naaticclenglishwise.ModalResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Generate_otp {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("otp")
    @Expose
    private Integer otp;
    @SerializedName("phone")
    @Expose
    private String phone;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getOtp() {
        return otp;
    }

    public void setOtp(Integer otp) {
        this.otp = otp;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Generate_otp{" +
                "message='" + message + '\'' +
                ", otp=" + otp +
                ", phone='" + phone + '\'' +
                '}';
    }
}