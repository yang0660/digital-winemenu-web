package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 产品信息
 */
@Data
@ApiModel(value = "产品信息")
public class ProductRmdRespVO {

    @ApiModelProperty(value = "产品ID")
    private Long productId;

    @ApiModelProperty(value = "美食ID")
    private Long foodId;

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "酒品图片")
    private String wineImg;

    @ApiModelProperty(value = "年份")
    private String vintageName;

    @ApiModelProperty(value = "价格")
    private Long regularPrice;

    @ApiModelProperty(value = "酒庄名称")
    private String wineryNameEng;

    @ApiModelProperty(value = "产地名称")
    private String rigionNameEng;

    @ApiModelProperty(value = "国家名称")
    private String countryNameNng;

    @ApiModelProperty(value = "葡萄种类")
    private String variety;



}