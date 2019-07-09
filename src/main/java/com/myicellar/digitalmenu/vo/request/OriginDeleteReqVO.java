package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 产地信息
 */
@Data
@ApiModel(value = "产地信息")
public class OriginDeleteReqVO {
    @ApiModelProperty(value = "产地ID")
    private Long wineOriginId;


}