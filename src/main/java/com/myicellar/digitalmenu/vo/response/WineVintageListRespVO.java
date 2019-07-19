package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 酒品年份返回信息
 */
@Data
@ApiModel(value = "酒品年份列表信息")
public class WineVintageListRespVO {

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "年份标签")
    private Long vintageTag;

    @ApiModelProperty(value = "风格")
    private  String style;

    @ApiModelProperty(value = "酒精度")
    private  BigDecimal acohol;

    @ApiModelProperty(value = "口味")
    private String descriptors;

    @ApiModelProperty(value = "葡萄")
    private String graps;

    @ApiModelProperty(value = "酒评")
    private  Date updatedAt;
}