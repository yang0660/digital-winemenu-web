package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 酒品属性
 */
@Data
@ApiModel(value = "酒品属性")
public class WineAttrMapRespVO {

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "属性分类ID")
    private Long attrCatgId;

    @ApiModelProperty(value = "属性列表")
    private List<WineAttrInfoRespVO> list;
}