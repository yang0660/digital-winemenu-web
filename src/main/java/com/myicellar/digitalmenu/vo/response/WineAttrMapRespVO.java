package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 酒品属性Map
 */
@Data
@ApiModel(value = "酒品属性Map")
public class WineAttrMapRespVO {

    @ApiModelProperty(value = "酒品ID|年份")
    private String wineVintageId;

    @ApiModelProperty(value = "属性分类ID")
    private Long attrCatgId;

    @ApiModelProperty(value = "属性列表")
    private List<WineAttrInfoRespVO> list;
}