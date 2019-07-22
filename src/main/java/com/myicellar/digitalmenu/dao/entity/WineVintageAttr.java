package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class WineVintageAttr {
    private Long wineId;

    private Long vintageTag;

    private Long attrId;

    private Short attrValNum;

    private Short micRank;

    private Date updatedAt;

    private String attrValStr;

    public Long getWineId() {
        return wineId;
    }

    public void setWineId(Long wineId) {
        this.wineId = wineId;
    }

    public Long getVintageTag() {
        return vintageTag;
    }

    public void setVintageTag(Long vintageTag) {
        this.vintageTag = vintageTag;
    }

    public Long getAttrId() {
        return attrId;
    }

    public void setAttrId(Long attrId) {
        this.attrId = attrId;
    }

    public Short getAttrValNum() {
        return attrValNum;
    }

    public void setAttrValNum(Short attrValNum) {
        this.attrValNum = attrValNum;
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

    public String getAttrValStr() {
        return attrValStr;
    }

    public void setAttrValStr(String attrValStr) {
        this.attrValStr = attrValStr;
    }
}