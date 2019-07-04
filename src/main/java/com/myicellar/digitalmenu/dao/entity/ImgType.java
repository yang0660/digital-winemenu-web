package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class ImgType {
    private Long imgTypeId;

    private String imgTypeNameEng;

    private String imgTypeNameChs;

    private String imgTypeNameCht;

    private Long createdUser;

    private Date createdAt;

    private Long updatedUser;

    private Date updatedAt;

    public Long getImgTypeId() {
        return imgTypeId;
    }

    public void setImgTypeId(Long imgTypeId) {
        this.imgTypeId = imgTypeId;
    }

    public String getImgTypeNameEng() {
        return imgTypeNameEng;
    }

    public void setImgTypeNameEng(String imgTypeNameEng) {
        this.imgTypeNameEng = imgTypeNameEng;
    }

    public String getImgTypeNameChs() {
        return imgTypeNameChs;
    }

    public void setImgTypeNameChs(String imgTypeNameChs) {
        this.imgTypeNameChs = imgTypeNameChs;
    }

    public String getImgTypeNameCht() {
        return imgTypeNameCht;
    }

    public void setImgTypeNameCht(String imgTypeNameCht) {
        this.imgTypeNameCht = imgTypeNameCht;
    }

    public Long getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Long createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(Long updatedUser) {
        this.updatedUser = updatedUser;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}