package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 后台美食详情
 */
@Data
@ApiModel(value = "后台美食详情")
public class FoodDetailBGRespVO {

    @ApiModelProperty(value = "供应商ID")
    private String supplierId;

    @ApiModelProperty(value = "供应商名称-英")
    private String supplierNameEng;

    @ApiModelProperty(value = "美食分类ID")
    private Long foodTypeId;

    @ApiModelProperty(value = "美食分类名称-英")
    private String foodTypeNameEng;

    @ApiModelProperty(value = "美食名称-英")
    private String foodNameEng;

    @ApiModelProperty(value = "美食名称-简")
    private String foodNameChs;

    @ApiModelProperty(value = "美食名称-繁")
    private String foodNameCht;

    @ApiModelProperty(value = "图片ID")
    private Long foodImgId;

    @ApiModelProperty(value = "图片")
    private String imgUrl = "";

    @ApiModelProperty(value = "美食描述-英")
    private String notePlainEng;

    @ApiModelProperty(value = "美食描述-简")
    private String notePlainChs;

    @ApiModelProperty(value = "美食描述-繁")
    private String notePlainCht;

    @ApiModelProperty(value = "美食价格")
    private BigDecimal price;

    @ApiModelProperty(value = "美食推荐")
    private Byte isRecommend;

}