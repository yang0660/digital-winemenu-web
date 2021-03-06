package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class WineVintageScore {
    private Long wineId;

    private Long vintageTag;

    private Long wineCriticsId;

    private Byte type;

    private Short scoreValNum;

    private String scoreValStr;

    private String scoreName;

    private Short scoreYear;

    private Date tastedAt;

    private Date updatedAt;

    private String notePlain;

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

    public Long getWineCriticsId() {
        return wineCriticsId;
    }

    public void setWineCriticsId(Long wineCriticsId) {
        this.wineCriticsId = wineCriticsId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Short getScoreValNum() {
        return scoreValNum;
    }

    public void setScoreValNum(Short scoreValNum) {
        this.scoreValNum = scoreValNum;
    }

    public String getScoreValStr() {
        return scoreValStr;
    }

    public void setScoreValStr(String scoreValStr) {
        this.scoreValStr = scoreValStr;
    }

    public String getScoreName() {
        return scoreName;
    }

    public void setScoreName(String scoreName) {
        this.scoreName = scoreName;
    }

    public Short getScoreYear() {
        return scoreYear;
    }

    public void setScoreYear(Short scoreYear) {
        this.scoreYear = scoreYear;
    }

    public Date getTastedAt() {
        return tastedAt;
    }

    public void setTastedAt(Date tastedAt) {
        this.tastedAt = tastedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNotePlain() {
        return notePlain;
    }

    public void setNotePlain(String notePlain) {
        this.notePlain = notePlain;
    }
}