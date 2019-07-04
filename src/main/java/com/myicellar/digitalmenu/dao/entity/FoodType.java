package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class FoodType {
    private Long foodTypeId;

    private Long supplierId;

    private String foodTypeNameChs;

    private String foodTypeNameCht;

    private String foodTypeNameEng;

    private Long createdBy;

    private Date createdAt;

    private Long updatedBy;

    private Date updatedAt;

    public Long getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(Long foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}