package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 评价获奖返回信息
 */
@Data
@ApiModel(value = "评价获奖返回信息")
public class ScoreRespVO {

    @ApiModelProperty(value = "评价分数")
    private Long scoreValStr;

    @ApiModelProperty(value = "评价分数")
    private Long scoreMax;

    @ApiModelProperty(value = "酒品评价")
    private String criticsNameEng;

    @ApiModelProperty(value = "获奖名称")
    private String scoreName;

    @ApiModelProperty(value = "获奖年份")
    private Integer scoreYear;
}