package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 酒品信息
*/
@Data
@ApiModel(value = "酒品信息")
public class WineDeleteReqVO {

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;
}