package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
* 美食详情
*/
@Data
@ApiModel(value = "美食详情")
public class FoodDisplayRespVO {

    @ApiModelProperty(value = "美食ID")
    private Long foodId;

    @ApiModelProperty(value = "美食名称-英")
    private String foodNameEng;

    @ApiModelProperty(value = "美食名称-简")
    private String foodNameChs;

    @ApiModelProperty(value = "美食名称-繁")
    private String foodNameCht;

    @ApiModelProperty(value = "图片-原图")
    private String imgUrl;

    @ApiModelProperty(value = "图片-缩略图")
    private String smallImgUrl;

    @ApiModelProperty(value = "美食价格")
    private BigDecimal price;

}