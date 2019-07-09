package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 酒品类型删除信息
*/
@Data
@ApiModel(value = "酒品类型删除信息")
public class WineTypeDeleteReqVO {

    @ApiModelProperty(value = "酒品类型ID")
    private Long wineTypeId;
}