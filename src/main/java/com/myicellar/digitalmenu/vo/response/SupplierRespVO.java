package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 供应商信息
 */
@Data
@ApiModel(value = "供应商信息")
public class SupplierRespVO {

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "供应商名称缩写")
    private String supplierSeoName;

    @ApiModelProperty(value = "供应商名称-英")
    private String supplierNameEng;

    @ApiModelProperty(value = "供应商名称-繁")
    private String supplierNameCht;

    @ApiModelProperty(value = "供应商名称-简")
    private String supplierNameChs;

    @ApiModelProperty(value = "供应商类型 0 online, 1 offline")
    private Byte type;

    @ApiModelProperty(value = "供应商LogoID")
    private Long logoImgId;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "更新用户")
    private Long updatedBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "创建用户")
    private Long createdBy;


}