package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 酒品年份信息
 */
@Data
@ApiModel(value = "酒品年份信息")
public class WineVintageReqVO {

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "年份标签")
    private Long vintageTag;

    @ApiModelProperty(value = "风格属性ID")
    private Long styleId;

    @ApiModelProperty(value = "酒精度")
    private  BigDecimal acohol;

    @ApiModelProperty(value = "口味属性ID列表")
    private List<Long> descriptorIds;

    @ApiModelProperty(value = "葡萄列表")
    private List<GrapReqVO> graps;

    @ApiModelProperty(value = "评分列表")
    private List<ScoreReqVO> scores;

    @ApiModelProperty(value = "获奖列表")
    private List<AwardReqVO> awards;

    @ApiModelProperty(value = "酒评")
    private  String tastingNote;
}