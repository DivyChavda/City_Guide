package com.example.divy.cityguide;

public class informationDataModel {

    private String LanguageTitle = "";
    private String Desc = "";
    private int imgSrc;
    private String reting ="";

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

    public void setImgSrc(int _imgSrc) {
        this.imgSrc = _imgSrc;
    }
    public int getImgSrc() {
        return this.imgSrc;
    }

    public void setreting(String _reting){
        this.reting = _reting;
    }

    public String getReting(){
        return this.reting;
    }
}
