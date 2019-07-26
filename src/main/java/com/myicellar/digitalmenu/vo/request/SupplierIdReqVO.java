package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 供应商详情查询参数
 */
@Data
@ApiModel(value = "供应商详情查询参数")
public class SupplierIdReqVO {

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;
}