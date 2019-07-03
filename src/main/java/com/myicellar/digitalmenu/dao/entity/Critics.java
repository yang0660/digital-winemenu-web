package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class Critics {
    private Long wineCriticsId;

    private String criticsSeoName;

    private String criticsNameEng;

    private String criticsNameCht;

    private String criticsNameChs;

    private Short micRank;

    private Short scoreMax;

    private Short scoreFactor;

    private String aboutUrl;

    private Date updatedAt;

    public Long getWineCriticsId() {
        return wineCriticsId;
    }

    public void setWineCriticsId(Long wineCriticsId) {
        this.wineCriticsId = wineCriticsId;
    }

    public String getCriticsSeoName() {
        return criticsSeoName;
    }

    public void setCriticsSeoName(String criticsSeoName) {
        this.criticsSeoName = criticsSeoName;
    }

    public String getCriticsNameEng() {
        return criticsNameEng;
    }

    public void setCriticsNameEng(String criticsNameEng) {
        this.criticsNameEng = criticsNameEng;
    }

    public String getCriticsNameCht() {
        return criticsNameCht;
    }

    public void setCriticsNameCht(String criticsNameCht) {
        this.criticsNameCht = criticsNameCht;
    }

    public String getCriticsNameChs() {
        return criticsNameChs;
    }

    public void setCriticsNameChs(String criticsNameChs) {
        this.criticsNameChs = criticsNameChs;
    }

    public Short getMicRank() {
        return micRank;
    }

    public void setMicRank(Short micRank) {
        this.micRank = micRank;
    }

    public Short getScoreMax() {
        return scoreMax;
    }

    public void setScoreMax(Short scoreMax) {
        this.scoreMax = scoreMax;
    }

    public Short getScoreFactor() {
        return scoreFactor;
    }

    public void setScoreFactor(Short scoreFactor) {
        this.scoreFactor = scoreFactor;
    }

    public String getAboutUrl() {
        return aboutUrl;
    }

    public void setAboutUrl(String aboutUrl) {
        this.aboutUrl = aboutUrl;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}