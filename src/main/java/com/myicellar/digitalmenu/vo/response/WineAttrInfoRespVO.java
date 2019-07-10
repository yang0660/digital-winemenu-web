package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 酒品属性
 */
@Data
@ApiModel(value = "酒品属性")
public class WineAttrInfoRespVO {

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "属性名称-英文")
    private String attrNameEng;

    @ApiModelProperty(value = "属性名称-简中")
    private String attrNameChs;

    @ApiModelProperty(value = "属性名称-繁中")
    private String attrNameCht;
}