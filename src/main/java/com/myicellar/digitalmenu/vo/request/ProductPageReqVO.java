package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 供应商关联酒品列表查询参数-分页
 */
@Data
@ApiModel(value = "供应商关联酒品列表查询参数-分页")
public class ProductPageReqVO extends PageRequestVO {
    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "酒品类型ID")
    private Long wineTypeId;

    @ApiModelProperty(value = "国家ID")
    private Long countryId;

    @ApiModelProperty(value = "酒品产地ID")
    private Long wineOriginId;

    @ApiModelProperty(value = "酒庄ID")
    private Long wineryId;

    @ApiModelProperty(value = "酒品名称-英")
    private String wineNameEng;

    @ApiModelProperty(value = "年份标签")
    private Long vintageTag;

    @ApiModelProperty(value = "美食ID")
    private Long foodId;

    @ApiModelProperty(value = "是否推荐 0 否，1 是")
    private Byte isRecommend;

    @ApiModelProperty(value = "0 全部，1 未关联美食， 2 已关联美食")
    private Byte isSelected;
}