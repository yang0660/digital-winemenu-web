package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 供应商关联酒品信息
 */
@Data
@ApiModel(value = "供应商关联酒品信息")
public class ProductRespVO {
    @ApiModelProperty(value = "供应商酒品关联ID")
    private Long productId;

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "年份标签")
    private Long vintageTag;

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "是否有机葡萄酒 0 否，1 是")
    private Byte isOrganicWine;

    @ApiModelProperty(value = "是否推荐 0 否，1 是")
    private Byte isRecommend;

    @ApiModelProperty(value = "容量价格配置列表")
    private List<VolumPriceRespVO> volumePrices;
}