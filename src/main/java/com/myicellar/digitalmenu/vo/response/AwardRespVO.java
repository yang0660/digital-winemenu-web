package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "获奖信息")
public class AwardRespVO {
    @ApiModelProperty(value = "颁奖机构ID")
    private Long criticsId;

    @ApiModelProperty(value = "颁奖年份")
    private  Short year;

    @ApiModelProperty(value = "奖项")
    private  String scoreName;
}