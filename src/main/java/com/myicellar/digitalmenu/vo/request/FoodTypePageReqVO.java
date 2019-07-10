package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 美食分类列表查询参数-分页
*/
@Data
@ApiModel(value = "美食分类列表查询参数-分页")
public class FoodTypePageReqVO extends PageRequestVO{

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;
}