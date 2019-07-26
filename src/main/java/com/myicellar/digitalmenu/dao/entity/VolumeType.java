package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class VolumeType {
    private Long volumeTypeId;

    private String volumeTypeCode;

    private String typeNameEng;

    private Long volInMl;

    private Byte type;

    private Integer micRank;

    private Date updatedAt;

    public Long getVolumeTypeId() {
        return volumeTypeId;
    }

    public void setVolumeTypeId(Long volumeTypeId) {
        this.volumeTypeId = volumeTypeId;
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

    public Long getVolInMl() {
        return volInMl;
    }

    public void setVolInMl(Long volInMl) {
        this.volInMl = volInMl;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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
}