package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class WineVintage {
    private Long wineId;

    private Long vintageTag;

    private String vintageName;

    private Long alcoholBps;

    private Byte isOrganicWine;

    private Date updatedAt;

    private String tastingNote;

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

    public Byte getIsOrganicWine() {
        return isOrganicWine;
    }

    public void setIsOrganicWine(Byte isOrganicWine) {
        this.isOrganicWine = isOrganicWine;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTastingNote() {
        return tastingNote;
    }

    public void setTastingNote(String tastingNote) {
        this.tastingNote = tastingNote;
    }
}