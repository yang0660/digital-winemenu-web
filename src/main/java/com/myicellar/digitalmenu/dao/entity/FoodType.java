package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class FoodType {
    private Long foodTypeId;

    private Long restaurantId;

    private String foodTypeNameChs;

    private String foodTypeNameCht;

    private String foodTypeNameEng;

    private Long createUser;

    private Date createTime;

    private Long updateUser;

    private Date updateTime;

    public Long getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(Long foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getFoodTypeNameChs() {
        return foodTypeNameChs;
    }

    public void setFoodTypeNameChs(String foodTypeNameChs) {
        this.foodTypeNameChs = foodTypeNameChs;
    }

    public String getFoodTypeNameCht() {
        return foodTypeNameCht;
    }

    public void setFoodTypeNameCht(String foodTypeNameCht) {
        this.foodTypeNameCht = foodTypeNameCht;
    }

    public String getFoodTypeNameEng() {
        return foodTypeNameEng;
    }

    public void setFoodTypeNameEng(String foodTypeNameEng) {
        this.foodTypeNameEng = foodTypeNameEng;
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