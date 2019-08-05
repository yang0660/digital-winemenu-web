package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 供应商状态参数
 */
@Data
@ApiModel(value = "供应商状态参数")
public class SupplierStatusReqVO {

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "供应商状态码")
    private Byte isEnabled;
}