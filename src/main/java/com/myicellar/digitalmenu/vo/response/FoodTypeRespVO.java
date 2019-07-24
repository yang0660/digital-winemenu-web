package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 美食分类信息
 */
@Data
@ApiModel(value = "美食分类信息")
public class FoodTypeRespVO {

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "供应商名称-英")
    private String supplierNameEng;

    @ApiModelProperty(value = "美食分类ID")
    private Long foodTypeId;

    @ApiModelProperty(value = "美食分类名称-英文")
    private String foodTypeNameEng;

    @ApiModelProperty(value = "美食分类名称-简体中文")
    private String foodTypeNameChs;

    @ApiModelProperty(value = "美食分类名称-繁体中文")
    private String foodTypeNameCht;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;
}