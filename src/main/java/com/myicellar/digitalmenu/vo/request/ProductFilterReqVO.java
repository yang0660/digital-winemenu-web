package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 筛选结果请求信息
 */
@Data
@ApiModel(value = "筛选结果请求信息")
public class ProductFilterReqVO extends PageRequestVO {

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "国家Id")
    private Long countryId;

    @ApiModelProperty(value = "产地Id")
    private Long wineOriginId;

    @ApiModelProperty(value = "原料ID")
    private Long attrId;

    @ApiModelProperty(value = "最低价格")
    private BigDecimal priceMin;

    @ApiModelProperty(value = "最高价格")
    private BigDecimal priceMax;

    @ApiModelProperty(value = "酒品类型ID")
    private Long wineTypeId;


}