package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 酒品年份删除参数
 */
@Data
@ApiModel(value = "酒品年份删除参数")
public class WineVintageDeleteReqVO {

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "年份标签")
    private Long vintageTag;
}