package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 美食列表参数-分页
*/
@Data
@ApiModel(value = "美食列表参数-分页")
public class FoodPageReqVO extends PageRequestVO{

    @ApiModelProperty(value = "美食ID")
    private Long foodId;

    @ApiModelProperty(value = "美食列表ID")
    private Long foodTypeId;

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;
}