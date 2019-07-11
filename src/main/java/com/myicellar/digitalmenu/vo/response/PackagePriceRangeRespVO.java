package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 供应商酒品价格区间
 */
@Data
@ApiModel(value = "供应商酒品价格区间")
public class PackagePriceRangeRespVO {

    @ApiModelProperty(value = "最低价格")
    private BigDecimal minPackagePrice = new BigDecimal(0.00);

    @ApiModelProperty(value = "最高价格")
    private BigDecimal maxPackagePrice = new BigDecimal(10000.00);

}