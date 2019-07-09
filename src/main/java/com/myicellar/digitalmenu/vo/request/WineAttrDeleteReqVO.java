package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 酒品-配料/口味关联信息删除信息
*/
@Data
@ApiModel(value = "酒品-配料/口味关联信息删除信息")
public class WineAttrDeleteReqVO {

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;
}