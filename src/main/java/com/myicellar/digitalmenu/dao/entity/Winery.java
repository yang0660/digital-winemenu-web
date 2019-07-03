package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class Winery {
    private Long wineryId;

    private String winerySeoName;

    private String wineryNameEng;

    private String wineryNameChs;

    private String wineryNameCht;

    private Long logoImgId;

    private Long bannerImgId;

    private Date updatedAt;

    private Long updatedUser;

    private Date createdAt;

    private Long createdUser;

    private String notePlainEng;

    private String notePlainChs;

    private String notePlainCht;

    private String aboutUrl;

    private String wineryImgIds;

    public Long getWineryId() {
        return wineryId;
    }

    public void setWineryId(Long wineryId) {
        this.wineryId = wineryId;
    }

    public String getWinerySeoName() {
        return winerySeoName;
    }

    public void setWinerySeoName(String winerySeoName) {
        this.winerySeoName = winerySeoName;
    }

    public String getWineryNameEng() {
        return wineryNameEng;
    }

    public void setWineryNameEng(String wineryNameEng) {
        this.wineryNameEng = wineryNameEng;
    }

    public String getWineryNameChs() {
        return wineryNameChs;
    }

    public void setWineryNameChs(String wineryNameChs) {
        this.wineryNameChs = wineryNameChs;
    }

    public String getWineryNameCht() {
        return wineryNameCht;
    }

    public void setWineryNameCht(String wineryNameCht) {
        this.wineryNameCht = wineryNameCht;
    }

    public Long getLogoImgId() {
        return logoImgId;
    }

    public void setLogoImgId(Long logoImgId) {
        this.logoImgId = logoImgId;
    }

    public Long getBannerImgId() {
        return bannerImgId;
    }

    public void setBannerImgId(Long bannerImgId) {
        this.bannerImgId = bannerImgId;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(Long updatedUser) {
        this.updatedUser = updatedUser;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Long createdUser) {
        this.createdUser = createdUser;
    }

    public String getNotePlainEng() {
        return notePlainEng;
    }

    public void setNotePlainEng(String notePlainEng) {
        this.notePlainEng = notePlainEng;
    }

    public String getNotePlainChs() {
        return notePlainChs;
    }

    public void setNotePlainChs(String notePlainChs) {
        this.notePlainChs = notePlainChs;
    }

    public String getNotePlainCht() {
        return notePlainCht;
    }

    public void setNotePlainCht(String notePlainCht) {
        this.notePlainCht = notePlainCht;
    }

    public String getAboutUrl() {
        return aboutUrl;
    }

    public void setAboutUrl(String aboutUrl) {
        this.aboutUrl = aboutUrl;
    }

    public String getWineryImgIds() {
        return wineryImgIds;
    }

    public void setWineryImgIds(String wineryImgIds) {
        this.wineryImgIds = wineryImgIds;
    }
}