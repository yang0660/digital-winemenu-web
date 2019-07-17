package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class WineVintageScore {
    private Long vintageScoreId;

    private Long wineId;

    private Short vintageTag;

    private Integer wineCriticsId;

    private Long scoreCatgId;

    private Short scoreValNum;

    private Short scoreYear;

    private Date tastedAt;

    private Date updatedAt;

    private String scoreValStr;

    private String scoreName;

    private String notePlain;

    public Long getVintageScoreId() {
        return vintageScoreId;
    }

    public void setVintageScoreId(Long vintageScoreId) {
        this.vintageScoreId = vintageScoreId;
    }

    public Long getWineId() {
        return wineId;
    }

    public void setWineId(Long wineId) {
        this.wineId = wineId;
    }

    public Short getVintageTag() {
        return vintageTag;
    }

    public void setVintageTag(Short vintageTag) {
        this.vintageTag = vintageTag;
    }

    public Integer getWineCriticsId() {
        return wineCriticsId;
    }

    public void setWineCriticsId(Integer wineCriticsId) {
        this.wineCriticsId = wineCriticsId;
    }

    public Long getScoreCatgId() {
        return scoreCatgId;
    }

    public void setScoreCatgId(Long scoreCatgId) {
        this.scoreCatgId = scoreCatgId;
    }

    public Short getScoreValNum() {
        return scoreValNum;
    }

    public void setScoreValNum(Short scoreValNum) {
        this.scoreValNum = scoreValNum;
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

    public String getNotePlain() {
        return notePlain;
    }

    public void setNotePlain(String notePlain) {
        this.notePlain = notePlain;
    }
}