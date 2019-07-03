package com.myicellar.digitalmenu.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private Long productId;

    private Long wineId;

    private String vintageTag;

    private Long supplierId;

    private BigDecimal regularPrice;

    private Long volumeTypeId;

    private BigDecimal glassPrice;

    private Long glassVolumeTypeId;

    private Byte isRecommend;

    private Byte isEnabled;

    private Date createdAt;

    private Long createdBy;

    private Date updatedAt;

    private Long updatedBy;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getWineId() {
        return wineId;
    }

    public void setWineId(Long wineId) {
        this.wineId = wineId;
    }

    public String getVintageTag() {
        return vintageTag;
    }

    public void setVintageTag(String vintageTag) {
        this.vintageTag = vintageTag;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
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