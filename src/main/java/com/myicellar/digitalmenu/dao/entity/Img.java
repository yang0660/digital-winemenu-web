package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class Img {
    private Long imgId;

    private Long imgTypeId;

    private String imgNameEng;

    private String imgNameChs;

    private String imgNameCht;

    private String imgUrl;

    private String smallImgUrl;

    private Long createdUser;

    private Date createdTime;

    private Long updatedUser;

    private Date updatedTime;

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public Long getImgTypeId() {
        return imgTypeId;
    }

    public void setImgTypeId(Long imgTypeId) {
        this.imgTypeId = imgTypeId;
    }

    public String getImgNameEng() {
        return imgNameEng;
    }

    public void setImgNameEng(String imgNameEng) {
        this.imgNameEng = imgNameEng;
    }

    public String getImgNameChs() {
        return imgNameChs;
    }

    public void setImgNameChs(String imgNameChs) {
        this.imgNameChs = imgNameChs;
    }

    public String getImgNameCht() {
        return imgNameCht;
    }

    public void setImgNameCht(String imgNameCht) {
        this.imgNameCht = imgNameCht;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSmallImgUrl() {
        return smallImgUrl;
    }

    public void setSmallImgUrl(String smallImgUrl) {
        this.smallImgUrl = smallImgUrl;
    }

    public Long getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Long createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(Long updatedUser) {
        this.updatedUser = updatedUser;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}