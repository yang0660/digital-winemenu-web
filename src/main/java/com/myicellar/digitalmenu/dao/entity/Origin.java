package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class Origin {
    private Long wineOriginId;

    private String regionSeoName;

    private String regionNameEng;

    private String regionNameChs;

    private String regionNameCht;

    private Long parentOriginId;

    private Long countryId;

    private Short micRank;

    private Date updatedAt;

    public Long getWineOriginId() {
        return wineOriginId;
    }

    public void setWineOriginId(Long wineOriginId) {
        this.wineOriginId = wineOriginId;
    }

    public String getRegionSeoName() {
        return regionSeoName;
    }

    public void setRegionSeoName(String regionSeoName) {
        this.regionSeoName = regionSeoName;
    }

    public String getRegionNameEng() {
        return regionNameEng;
    }

    public void setRegionNameEng(String regionNameEng) {
        this.regionNameEng = regionNameEng;
    }

    public String getRegionNameChs() {
        return regionNameChs;
    }

    public void setRegionNameChs(String regionNameChs) {
        this.regionNameChs = regionNameChs;
    }

    public String getRegionNameCht() {
        return regionNameCht;
    }

    public void setRegionNameCht(String regionNameCht) {
        this.regionNameCht = regionNameCht;
    }

    public Long getParentOriginId() {
        return parentOriginId;
    }

    public void setParentOriginId(Long parentOriginId) {
        this.parentOriginId = parentOriginId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
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