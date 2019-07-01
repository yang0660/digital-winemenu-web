package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class Restaurant {
    private Long restaurantId;

    private String restaurantNameChs;

    private String restaurantNameCht;

    private String restaurantNameEng;

    private String logoUrl;

    private String slogan;

    private String posterUrl;

    private Byte status;

    private Long createUser;

    private Date createTime;

    private Long updateUser;

    private Date updateTime;

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantNameChs() {
        return restaurantNameChs;
    }

    public void setRestaurantNameChs(String restaurantNameChs) {
        this.restaurantNameChs = restaurantNameChs;
    }

    public String getRestaurantNameCht() {
        return restaurantNameCht;
    }

    public void setRestaurantNameCht(String restaurantNameCht) {
        this.restaurantNameCht = restaurantNameCht;
    }

    public String getRestaurantNameEng() {
        return restaurantNameEng;
    }

    public void setRestaurantNameEng(String restaurantNameEng) {
        this.restaurantNameEng = restaurantNameEng;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}