package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 美食分类删除参数
 */
@Data
@ApiModel(value = "美食分类删除参数")
public class FoodTypeDeleteReqVO {

    @ApiModelProperty(value = "美食分类ID")
    private Long foodTypeId;
}