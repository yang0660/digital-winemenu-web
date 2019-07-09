package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 供应商酒品查询参数
*/
@Data
@ApiModel(value = "供应商酒品查询参数")
public class SupplierProductReqVO {

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "酒品类型ID")
    private Long wineTypeId;
}