package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class Attr {
    private Long attrId;

    private Long attrCatgId;

    private String attrCatgName;

    private String attrSeoName;

    private String attrNameEng;

    private String attrNameChs;

    private String attrNameCht;

    private Short micRank;

    private Date updatedAt;

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public Long getAttrCatgId() {
        return attrCatgId;
    }

    public void setAttrCatgId(Long attrCatgId) {
        this.attrCatgId = attrCatgId;
    }

    public String getAttrCatgName() {
        return attrCatgName;
    }

    public void setAttrCatgName(String attrCatgName) {
        this.attrCatgName = attrCatgName;
    }

    public String getAttrSeoName() {
        return attrSeoName;
    }

    public void setAttrSeoName(String attrSeoName) {
        this.attrSeoName = attrSeoName;
    }

    public String getAttrNameEng() {
        return attrNameEng;
    }

    public void setAttrNameEng(String attrNameEng) {
        this.attrNameEng = attrNameEng;
    }

    public String getAttrNameChs() {
        return attrNameChs;
    }

    public void setAttrNameChs(String attrNameChs) {
        this.attrNameChs = attrNameChs;
    }

    public String getAttrNameCht() {
        return attrNameCht;
    }

    public void setAttrNameCht(String attrNameCht) {
        this.attrNameCht = attrNameCht;
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