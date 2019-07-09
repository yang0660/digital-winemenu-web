package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 获奖信息删除信息
*/
@Data
@ApiModel(value = "获奖信息删除信息")
public class WineVintageScoreDeleteReqVO {

    @ApiModelProperty(value = "获奖信息ID")
    private Long vintageScoreId;
}