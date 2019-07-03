package com.myicellar.digitalmenu.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Food {
    private Long foodId;

    private Long foodTypeId;

    private String foodNameEng;

    private String foodNameChs;

    private String foodNameCht;

    private Long foodImgId;

    private BigDecimal price;

    private Byte isRecommend;

    private Byte isEnabled;

    private Long createdUser;

    private Date createdTime;

    private Long updatedUser;

    private Date updatedTime;

    private String notePlainEng;

    private String notePlainChs;

    private String notePlainCht;

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public Long getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(Long foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getFoodNameEng() {
        return foodNameEng;
    }

    public void setFoodNameEng(String foodNameEng) {
        this.foodNameEng = foodNameEng;
    }

    public String getFoodNameChs() {
        return foodNameChs;
    }

    public void setFoodNameChs(String foodNameChs) {
        this.foodNameChs = foodNameChs;
    }

    public String getFoodNameCht() {
        return foodNameCht;
    }

    public void setFoodNameCht(String foodNameCht) {
        this.foodNameCht = foodNameCht;
    }

    public Long getFoodImgId() {
        return foodImgId;
    }

    public void setFoodImgId(Long foodImgId) {
        this.foodImgId = foodImgId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Byte getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Byte getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Byte isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Long getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Long createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(Long updatedUser) {
        this.updatedUser = updatedUser;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
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