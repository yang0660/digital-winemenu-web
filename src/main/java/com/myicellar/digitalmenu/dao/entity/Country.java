package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class Country {
    private Long countryId;

    private String countryCode;

    private String countrySeoName;

    private String countryNameEng;

    private String countryNameChs;

    private String countryNameCht;

    private String imgUrl;

    private Short micRank;

    private Date updatedAt;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountrySeoName() {
        return countrySeoName;
    }

    public void setCountrySeoName(String countrySeoName) {
        this.countrySeoName = countrySeoName;
    }

    public String getCountryNameEng() {
        return countryNameEng;
    }

    public void setCountryNameEng(String countryNameEng) {
        this.countryNameEng = countryNameEng;
    }

    public String getCountryNameChs() {
        return countryNameChs;
    }

    public void setCountryNameChs(String countryNameChs) {
        this.countryNameChs = countryNameChs;
    }

    public String getCountryNameCht() {
        return countryNameCht;
    }

    public void setCountryNameCht(String countryNameCht) {
        this.countryNameCht = countryNameCht;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Short getMicRank() {
        return micRank;
    }

    public void setMicRank(Short micRank) {
        this.micRank = micRank;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}