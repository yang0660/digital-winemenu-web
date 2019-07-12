package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 酒品详情返回信息
 */
@Data
@ApiModel(value = "酒品详情返回信息")
public class PackageDetailRespVO {

    @ApiModelProperty(value = "商品ID")
    private Long packageId;

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "酒品图片ID")
    private Long wineImgId;

    @ApiModelProperty(value = "酒品图片-缩略图")
    private String wineSmallImgUrl;

    @ApiModelProperty(value = "酒品图片-原图")
    private String wineImgUrl;

    @ApiModelProperty(value = "酒品类型名称-英文")
    private String wineTypeNameEng;

    @ApiModelProperty(value = "酒品名称-英文")
    private String wineNameEng;

    @ApiModelProperty(value = "酒品年份标签")
    private Long vintageTag;

    @ApiModelProperty(value = "酒品年份")
    private String vintageName;

    @ApiModelProperty(value = "酒评")
    private String tastingNote;

    @ApiModelProperty(value = "国家-英文")
    private String countryNameEng;

    @ApiModelProperty(value = "产地-英文")
    private String regionNameEng;

    @ApiModelProperty(value = "原料")
    private String variety;

    @ApiModelProperty(value = "风格")
    private String style;

    @ApiModelProperty(value = "口味")
    private String descriptor;

    @ApiModelProperty(value = "酒精含量")
    private Long alcoholBps;

    @ApiModelProperty(value = "酒精度")
    private BigDecimal alcohol;

    @ApiModelProperty(value = "价格/一瓶")
    private BigDecimal regularPrice;

    @ApiModelProperty(value = "价格/一杯")
    private BigDecimal glassPrice;

    @ApiModelProperty(value = "酒庄名称-英文")
    private String wineryNameEng;

    @ApiModelProperty(value = "酒庄介绍-英文")
    private String notePlainEng;

    @ApiModelProperty(value = "酒庄官网")
    private String wineryAboutUrl;

    @ApiModelProperty(value = "酒庄LOGO图片ID")
    private Long wineryLogoId;

    @ApiModelProperty(value = "酒庄Logo-原图")
    private String wineryLogoUrl;

    @ApiModelProperty(value = "酒庄Logo-缩略图")
    private String wineryLogoSmallUrl;

    @ApiModelProperty(value = "酒庄banner图ID")
    private Long bannerImgId;

    @ApiModelProperty(value = "酒庄banner图-原图")
    private String bannerImgUrl;

    @ApiModelProperty(value = "酒庄banner图-缩略图")
    private String bannerSmallImgUrl;

    @ApiModelProperty(value = "酒庄图片IDs")
    private String wineryImgIds;

    @ApiModelProperty(value = "酒庄图片列表-原图")
    private List<String> wineryImgUrls;

    @ApiModelProperty(value = "酒庄图片列表-缩略图")
    private List<String> winerySmallImgUrls;

    @ApiModelProperty(value = "获奖名称")
    private String scoreName;

    @ApiModelProperty(value = "获奖年份")
    private Integer scoreYear;

    @ApiModelProperty(value = "评价分数")
    private String scoreValStr;

    @ApiModelProperty(value = "酒品评价")
    private String criticsNameEng;

    @ApiModelProperty(value = "评价总分")
    private String scoreMax;

    @ApiModelProperty(value = "酒品评价网页")
    private String criticsAboutUrl;
}