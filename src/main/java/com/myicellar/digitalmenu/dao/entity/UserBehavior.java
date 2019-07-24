package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class UserBehavior {
    private Long supplierId;

    private Long productId;

    private String deviceType;

    private String deviceId;

    private Byte type;

    private Date uploadAt;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getUploadAt() {
        return uploadAt;
    }

    public void setUploadAt(Date uploadAt) {
        this.uploadAt = uploadAt;
    }
}