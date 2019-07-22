package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 酒品年份参数
 */
@Data
@ApiModel(value = "酒品年份参数")
public class WineVintageInfoReqVO {

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "年份标签")
    private Long vintageTag;
}