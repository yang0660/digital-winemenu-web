package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 供应商关联酒品列表
 */
@Data
@ApiModel(value = "供应商关联酒品列表")
public class ProductListRespVO {
    @ApiModelProperty(value = "供应商酒品关联ID")
    private Long productId;

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "酒品名称")
    private String wineName;

    @ApiModelProperty(value = "年份标签")
    private Long vintageTage;

    @ApiModelProperty(value = "年份描述")
    private String vintageName;

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "是否有机葡萄酒 0 否，1 是")
    private Byte isOrganicWine;

    @ApiModelProperty(value = "是否推荐 0 否，1 是")
    private Byte isRecommend;

    @ApiModelProperty(value = "酒品分类ID")
    private Long wineTypeId;

    @ApiModelProperty(value = "酒品分类名称")
    private String wineTypeName;

    @ApiModelProperty(value = "国家ID")
    private Long countryId;

    @ApiModelProperty(value = "国家名称")
    private String countryName;

    @ApiModelProperty(value = "酒品产地ID")
    private Long wineOriginId;

    @ApiModelProperty(value = "酒品产地名称")
    private String wineOriginName;

    @ApiModelProperty(value = "酒庄ID")
    private Long wineryId;

    @ApiModelProperty(value = "酒庄名称")
    private String wineryName;

    @ApiModelProperty(value = "酒品图片")
    private Long wineImgId;

    @ApiModelProperty(value = "酒品图片")
    private String wineImgUrl = "";

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;
}