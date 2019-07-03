package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class Wine {
    private Long wineId;

    private Long wineTypeId;

    private Long wineOriginId;

    private Long wineryId;

    private String wineSeoName;

    private String wineNameEng;

    private String wineNameChs;

    private String wineNameCht;

    private Long wineImgId;

    private Date updatedAt;

    public Long getWineId() {
        return wineId;
    }

    public void setWineId(Long wineId) {
        this.wineId = wineId;
    }

    public Long getWineTypeId() {
        return wineTypeId;
    }

    public void setWineTypeId(Long wineTypeId) {
        this.wineTypeId = wineTypeId;
    }

    public Long getWineOriginId() {
        return wineOriginId;
    }

    public void setWineOriginId(Long wineOriginId) {
        this.wineOriginId = wineOriginId;
    }

    public Long getWineryId() {
        return wineryId;
    }

    public void setWineryId(Long wineryId) {
        this.wineryId = wineryId;
    }

    public String getWineSeoName() {
        return wineSeoName;
    }

    public void setWineSeoName(String wineSeoName) {
        this.wineSeoName = wineSeoName;
    }

    public String getWineNameEng() {
        return wineNameEng;
    }

    public void setWineNameEng(String wineNameEng) {
        this.wineNameEng = wineNameEng;
    }

    public String getWineNameChs() {
        return wineNameChs;
    }

    public void setWineNameChs(String wineNameChs) {
        this.wineNameChs = wineNameChs;
    }

    public String getWineNameCht() {
        return wineNameCht;
    }

    public void setWineNameCht(String wineNameCht) {
        this.wineNameCht = wineNameCht;
    }

    public Long getWineImgId() {
        return wineImgId;
    }

    public void setWineImgId(Long wineImgId) {
        this.wineImgId = wineImgId;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}