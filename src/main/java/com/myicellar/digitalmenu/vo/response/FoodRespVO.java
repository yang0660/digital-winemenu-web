package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 美食信息
 */
@Data
@ApiModel(value = "美食信息")
public class FoodRespVO {
    @ApiModelProperty(value = "美食ID")
    private Long foodId;

    @ApiModelProperty(value = "供应商名称-英")
    private String supplierNameEng;

    @ApiModelProperty(value = "美食分类名称-英")
    private String foodTypeNameEng;

    @ApiModelProperty(value = "美食名称-英")
    private String foodNameEng;

    @ApiModelProperty(value = "美食名称-简")
    private String foodNameChs;

    @ApiModelProperty(value = "美食名称-繁")
    private String foodNameCht;

    @ApiModelProperty(value = "美食图片ID")
    private Long foodImgId;

    @ApiModelProperty(value = "美食图片")
    private String foodImg;

    @ApiModelProperty(value = "美食推荐")
    private Byte isRecommend;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;


}