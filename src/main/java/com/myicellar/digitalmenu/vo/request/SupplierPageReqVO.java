package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 供应商相关信息查询参数-分页
*/
@Data
@ApiModel(value = "供应商相关信息查询参数-分页")
public class SupplierPageReqVO extends PageRequestVO{

    @ApiModelProperty(value = "供应商名称-英")
    private String supplierNameEng;

    @ApiModelProperty(value = "供应商类型-0:online; 1:offline")
    private Byte type;
}