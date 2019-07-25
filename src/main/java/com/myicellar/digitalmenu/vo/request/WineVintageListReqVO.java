package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 酒品年份列表查询参数
 */
@Data
@ApiModel(value = "酒品年份列表查询参数")
public class WineVintageListReqVO extends PageRequestVO{

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

}