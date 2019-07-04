package com.myicellar.digitalmenu.dao.entity;

import java.util.Date;

public class Supplier {
    private Long supplierId;

    private String supplierSeoName;

    private String supplierNameEng;

    private String supplierNameCht;

    private String supplierNameChs;

    private Byte suppilerType;

    private Long logoImgId;

    private Date updatedAt;

    private Long updatedBy;

    private Date createdAt;

    private Long createdBy;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierSeoName() {
        return supplierSeoName;
    }

    public void setSupplierSeoName(String supplierSeoName) {
        this.supplierSeoName = supplierSeoName;
    }

    public String getSupplierNameEng() {
        return supplierNameEng;
    }

    public void setSupplierNameEng(String supplierNameEng) {
        this.supplierNameEng = supplierNameEng;
    }

    public String getSupplierNameCht() {
        return supplierNameCht;
    }

    public void setSupplierNameCht(String supplierNameCht) {
        this.supplierNameCht = supplierNameCht;
    }

    public String getSupplierNameChs() {
        return supplierNameChs;
    }

    public void setSupplierNameChs(String supplierNameChs) {
        this.supplierNameChs = supplierNameChs;
    }

    public Byte getSuppilerType() {
        return suppilerType;
    }

    public void setSuppilerType(Byte suppilerType) {
        this.suppilerType = suppilerType;
    }

    public Long getLogoImgId() {
        return logoImgId;
    }

    public void setLogoImgId(Long logoImgId) {
        this.logoImgId = logoImgId;
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
}