package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 酒品年份返回信息
 */
@Data
@ApiModel(value = "酒品年份返回信息")
public class WineVintageRespVO {

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

    @ApiModel(value = "葡萄信息")
    private class GrapReqVO {
        @ApiModelProperty(value = "葡萄属性ID")
        private Long grapId;

        @ApiModelProperty(value = "葡萄比例")
        private BigDecimal rate;
    }

    @ApiModel(value = "评分信息")
    private class ScoreReqVO {
        @ApiModelProperty(value = "评论机构ID")
        private Long criticsId;

        @ApiModelProperty(value = "评分")
        private BigDecimal score;

        @ApiModelProperty(value = "评分日期")
        private Date tastedAt;
    }

    @ApiModel(value = "获奖信息")
    private class AwardReqVO {
        @ApiModelProperty(value = "颁奖机构ID")
        private Long criticsId;

        @ApiModelProperty(value = "颁奖年份")
        private  Integer year;

        @ApiModelProperty(value = "奖项")
        private  String scoreName;
    }
}