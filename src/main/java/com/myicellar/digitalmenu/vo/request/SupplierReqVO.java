package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 供应商新增/修改参数
 */
@Data
@ApiModel(value = "供应商新增/修改参数")
public class SupplierReqVO {

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
}