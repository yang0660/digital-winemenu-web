package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 供应商扫描量
 */
@Data
@ApiModel(value = "供应商扫描量")
public class SupplierReportRespVO {

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "日期")
    private String thisDate;

    @ApiModelProperty(value = "数量")
    private Integer num;
}