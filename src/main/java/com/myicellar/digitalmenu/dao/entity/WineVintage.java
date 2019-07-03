package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class WineVintage {
    private Long wineId;

    private Long vintageTag;

    private String vintageName;

    private Long alcoholBps;

    private Long wineImgId;

    private Date updatedAt;

    private String notePlainEng;

    private String notePlainChs;

    private String notePlainCht;

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

    public String getVintageName() {
        return vintageName;
    }

    public void setVintageName(String vintageName) {
        this.vintageName = vintageName;
    }

    public Long getAlcoholBps() {
        return alcoholBps;
    }

    public void setAlcoholBps(Long alcoholBps) {
        this.alcoholBps = alcoholBps;
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
}