package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ApiModel(value = "获奖信息")
public class AwardReqVO {
    @ApiModelProperty(value = "颁奖机构ID")
    private Long criticsId;

    @ApiModelProperty(value = "颁奖年份")
    private  Integer year;

    @ApiModelProperty(value = "奖项")
    private  String scoreName;
}