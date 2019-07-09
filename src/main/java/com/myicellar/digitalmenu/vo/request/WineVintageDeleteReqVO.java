package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 酒品年份
*/
@Data
@ApiModel(value = "酒品年份")
public class WineVintageDeleteReqVO {

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;
}