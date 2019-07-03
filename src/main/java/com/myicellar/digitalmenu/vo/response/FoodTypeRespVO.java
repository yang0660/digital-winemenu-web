package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 美食分类信息
*/
@Data
@ApiModel(value = "美食分类信息")
public class FoodTypeRespVO {

    /**
    * 供应商ID
    */
    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    /**
     * 供应商ID
     */
    @ApiModelProperty(value = "美食分类ID")
    private Long foodTypeId;

    /**
     * 供应商ID
     */
    @ApiModelProperty(value = "美食分类名称-英文")
    private String foodTypeNameEng;

    /**
     * 供应商ID
     */
    @ApiModelProperty(value = "美食分类名称-简体中文")
    private String foodTypeNameChs;

    /**
     * 供应商ID
     */
    @ApiModelProperty(value = "美食分类名称-繁体中文")
    private String foodTypeNameCht;
}