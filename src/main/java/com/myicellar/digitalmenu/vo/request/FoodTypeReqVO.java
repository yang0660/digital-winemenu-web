package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 美食分类信息
*/
@Data
@ApiModel(value = "美食分类信息")
public class FoodTypeReqVO {

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "美食分类ID(新增时，不需要传)")
    private Long foodTypeId;

    @ApiModelProperty(value = "美食分类名称-英文")
    private String foodTypeNameEng;

    @ApiModelProperty(value = "美食分类名称-简体中文")
    private String foodTypeNameChs;

    @ApiModelProperty(value = "美食分类名称-繁体中文")
    private String foodTypeNameCht;
}