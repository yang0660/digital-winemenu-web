package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 美食新增/修改参数
 */
@Data
@ApiModel(value = "美食新增/修改参数")
public class FoodReqVO {

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "美食ID-新增时不需要传")
    private Long foodId;

    @ApiModelProperty(value = "美食分类ID")
    private Long foodTypeId;

    @ApiModelProperty(value = "美食名称-英")
    private String foodNameEng;

    @ApiModelProperty(value = "美食名称-简")
    private String foodNameChs;

    @ApiModelProperty(value = "美食名称-繁")
    private String foodNameCht;

    @ApiModelProperty(value = "美食图片ID")
    private Long foodImgId;

    @ApiModelProperty(value = "美食价格")
    private BigDecimal price;

    @ApiModelProperty(value = "美食推荐")
    private Byte isRecommend;

    @ApiModelProperty(value = "美食启用")
    private Byte isEnabled;

    @ApiModelProperty(value = "美食描述-英")
    private String notePlainEng;

    @ApiModelProperty(value = "美食描述-简")
    private String notePlainChs;

    @ApiModelProperty(value = "美食描述-繁")
    private String notePlainCht;


}