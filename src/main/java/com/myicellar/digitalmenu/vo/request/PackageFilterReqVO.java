package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 筛选结果请求信息
 */
@Data
@ApiModel(value = "筛选结果请求信息")
public class PackageFilterReqVO extends PageRequestVO {

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "酒品类型ID集合")
    private Long wineTypeIds;

    @ApiModelProperty(value = "国家ID集合")
    private List<Long> countryIds;

    @ApiModelProperty(value = "产地ID集合")
    private List<Long> wineOriginIds;

    @ApiModelProperty(value = "原料ID集合")
    private List<Long> attrIds;

    @ApiModelProperty(value = "最低价格")
    private BigDecimal priceMin;

    @ApiModelProperty(value = "最高价格")
    private BigDecimal priceMax;

    @ApiModelProperty(value = "是否有机葡萄酒：0 否，1 是")
    private Byte isOrganicWine;

    @ApiModelProperty(value = "是否杯装酒：0 否，1 是")
    private Byte isGlassWine;
}