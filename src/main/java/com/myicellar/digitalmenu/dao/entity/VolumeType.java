package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class VolumeType {
    private Long volumeTypeId;

    private Long volInMl;

    private Integer micRank;

    private Date updatedAt;

    private String volumeTypeCode;

    private String typeNameEng;

    public Long getVolumeTypeId() {
        return volumeTypeId;
    }

    public void setVolumeTypeId(Long volumeTypeId) {
        this.volumeTypeId = volumeTypeId;
    }

    public Long getVolInMl() {
        return volInMl;
    }

    public void setVolInMl(Long volInMl) {
        this.volInMl = volInMl;
    }

    public Integer getMicRank() {
        return micRank;
    }

    public void setMicRank(Integer micRank) {
        this.micRank = micRank;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getVolumeTypeCode() {
        return volumeTypeCode;
    }

    public void setVolumeTypeCode(String volumeTypeCode) {
        this.volumeTypeCode = volumeTypeCode;
    }

    public String getTypeNameEng() {
        return typeNameEng;
    }

    public void setTypeNameEng(String typeNameEng) {
        this.typeNameEng = typeNameEng;
    }
}