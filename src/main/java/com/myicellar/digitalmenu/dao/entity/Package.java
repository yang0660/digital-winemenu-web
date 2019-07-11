package com.myicellar.digitalmenu.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Package {
    private Long packageId;

    private Long productId;

    private BigDecimal regularPrice;

    private Long volumeTypeId;

    private BigDecimal glassPrice;

    private Long glassVolumeTypeId;

    private Date createdAt;

    private Long createdBy;

    private Date updatedAt;

    private Long updatedBy;

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(BigDecimal regularPrice) {
        this.regularPrice = regularPrice;
    }

    public Long getVolumeTypeId() {
        return volumeTypeId;
    }

    public void setVolumeTypeId(Long volumeTypeId) {
        this.volumeTypeId = volumeTypeId;
    }

    public BigDecimal getGlassPrice() {
        return glassPrice;
    }

    public void setGlassPrice(BigDecimal glassPrice) {
        this.glassPrice = glassPrice;
    }

    public Long getGlassVolumeTypeId() {
        return glassVolumeTypeId;
    }

    public void setGlassVolumeTypeId(Long glassVolumeTypeId) {
        this.glassVolumeTypeId = glassVolumeTypeId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }
}