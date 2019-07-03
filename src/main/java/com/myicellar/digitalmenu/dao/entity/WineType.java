package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class WineType {
    private Long wineTypeId;

    private String wineTypeSeoName;

    private String wineTypeNameEng;

    private String wineTypeNameChs;

    private String wineTypeNameCht;

    private Short micRank;

    private Date updatedAt;

    public Long getWineTypeId() {
        return wineTypeId;
    }

    public void setWineTypeId(Long wineTypeId) {
        this.wineTypeId = wineTypeId;
    }

    public String getWineTypeSeoName() {
        return wineTypeSeoName;
    }

    public void setWineTypeSeoName(String wineTypeSeoName) {
        this.wineTypeSeoName = wineTypeSeoName;
    }

    public String getWineTypeNameEng() {
        return wineTypeNameEng;
    }

    public void setWineTypeNameEng(String wineTypeNameEng) {
        this.wineTypeNameEng = wineTypeNameEng;
    }

    public String getWineTypeNameChs() {
        return wineTypeNameChs;
    }

    public void setWineTypeNameChs(String wineTypeNameChs) {
        this.wineTypeNameChs = wineTypeNameChs;
    }

    public String getWineTypeNameCht() {
        return wineTypeNameCht;
    }

    public void setWineTypeNameCht(String wineTypeNameCht) {
        this.wineTypeNameCht = wineTypeNameCht;
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