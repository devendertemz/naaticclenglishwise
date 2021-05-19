package com.englishwise.naaticclenglishwise.ModalResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MockRespBean {


    @SerializedName("did")
    @Expose
    private String did;
    @SerializedName("category_name")
    @Expose
    private String categoryName;

    public MockRespBean(String categoryName) {
        this.categoryName = categoryName;
    }

    @SerializedName("language_id")
    @Expose
    private String languageId;

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

}