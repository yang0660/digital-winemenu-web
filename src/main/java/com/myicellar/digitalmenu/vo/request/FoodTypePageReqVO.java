package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 供应商信息查询参数
*/
@Data
@ApiModel(value = "供应商信息查询参数")
public class FoodTypePageReqVO extends PageRequestVO{

    /**
    * 供应商ID
    */
    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;
}