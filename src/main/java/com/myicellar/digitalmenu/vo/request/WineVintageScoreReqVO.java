package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 获奖信息
 */
@Data
@ApiModel(value = "获奖信息")
public class WineVintageScoreReqVO {
    @ApiModelProperty(value = "获奖ID")
    private Long vintageScoreId;

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "年份标签")
    private Short vintageTag;

    @ApiModelProperty(value = "评价ID")
    private Integer wineCriticsId;

    @ApiModelProperty(value = "获奖目录ID")
    private Long scoreCatgId;

    @ApiModelProperty(value = "获奖分数-数字")
    private Short scoreValNum;

    @ApiModelProperty(value = "获奖年份")
    private Short scoreYear;

    @ApiModelProperty(value = "品尝地点")
    private Date tastedAt;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "获奖分数-字符串")
    private String scoreValStr;

    @ApiModelProperty(value = "奖项名称")
    private String scoreName;

    @ApiModelProperty(value = "描述-英")
    private String notePlainEng;

    @ApiModelProperty(value = "描述-简")
    private String notePlainChs;

    @ApiModelProperty(value = "描述-繁")
    private String notePlainCht;


}