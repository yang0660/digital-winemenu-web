package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品详细信息
 */
@Data
@ApiModel(value = "商品详细信息")
public class ProductInfoRespVO {

    @ApiModelProperty(value = "productId")
    private Long productId;

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "酒品类型名称-英文")
    private String wineTypeNameEng;

    @ApiModelProperty(value = "酒品名称-英文")
    private String wineNameEng;

    @ApiModelProperty(value = "酒品名称-简中")
    private String wineNameChs;

    @ApiModelProperty(value = "酒品名称-繁中")
    private String wineNameCht;

    @ApiModelProperty(value = "酒庄ID")
    private Long wineryId;

    @ApiModelProperty(value = "酒庄名称-英文")
    private String wineryNameEng;

    @ApiModelProperty(value = "年份标签")
    private Long vintageTag;

    @ApiModelProperty(value = "年份名称")
    private String vintageName;

    @ApiModelProperty(value = "国家-英文")
    private String countryNameEng;

    @ApiModelProperty(value = "国家-简中")
    private String countryNameChs;

    @ApiModelProperty(value = "国家-繁中")
    private String countryNameCht;

    @ApiModelProperty(value = "国家图标url")
    private String countryImgUrl;

    @ApiModelProperty(value = "产地-英文")
    private String regionNameEng;

    @ApiModelProperty(value = "产地-简中")
    private String regionNameChs;

    @ApiModelProperty(value = "产地-简中")
    private String regionNameCht;

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "价格/一瓶")
    private BigDecimal regularPrice;

    @ApiModelProperty(value = "价格/一杯")
    private BigDecimal glassPrice;

    @ApiModelProperty(value = "酒庄LOGO图片ID")
    private Long wineryLogoId;

    @ApiModelProperty(value = "酒庄Logo-原图")
    private String wineryLogoUrl;

    @ApiModelProperty(value = "酒品图片ID")
    private Long wineImgId;

    @ApiModelProperty(value = "酒品图片-原图")
    private String wineImgUrl;

    @ApiModelProperty(value = "原料")
    private String variety;
}