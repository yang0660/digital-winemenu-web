package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 供应商删除参数
 */
@Data
@ApiModel(value = "供应商删除参数")
public class SupplierDeleteReqVO {

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;
}