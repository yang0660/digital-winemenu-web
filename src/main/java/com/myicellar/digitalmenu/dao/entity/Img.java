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

    private Long createdBy;

    private Date createdAt;

    private Long updatedBy;

    private Date updatedAt;

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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}