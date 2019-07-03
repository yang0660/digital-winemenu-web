package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 美食分类信息
*/
@Data
@ApiModel(value = "美食分类信息")
public class FoodTypeDeleteReqVO {

    /**
     * 美食分类ID
     */
    @ApiModelProperty(value = "美食分类ID")
    private Long foodTypeId;
}