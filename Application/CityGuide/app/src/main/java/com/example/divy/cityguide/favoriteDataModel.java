package com.example.divy.cityguide;

public class favoriteDataModel {
    private String LanguageTitle = "";
    private String imgSrc;
    private String Desc = "";
    private String UserID;
    private String BusinessID;

    public void setLanguageTitle(String _LanguageTitle) {
        this.LanguageTitle = _LanguageTitle;
    }
    public String getLanguageTitle() {
        return this.LanguageTitle;
    }

    public void setDesc(String _Desc) {
        this.Desc = _Desc;
    }
    public String getDesc() {
        return this.Desc;
    }

    public void setImgSrc(String _imgSrc) {
        this.imgSrc = _imgSrc;
    }
    public String getImgSrc() {
        return this.imgSrc;
    }

    public void setUserID(String id) {
        this.UserID = id;
    }

    public String getUserID() {
        return UserID;
    }

    public void setBusinessID(String id) {
        this.BusinessID = id;
    }

    public String getBusinessID() {
        return BusinessID;
    }
}
