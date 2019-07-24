package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 酒品相关信息查询参数
 */
@Data
@ApiModel(value = "酒品相关信息查询参数")
public class WineRespVO {
    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

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

    @ApiModelProperty(value = "酒品名称-英")
    private String wineNameEng;

    @ApiModelProperty(value = "酒品名称-简")
    private String wineNameChs;

    @ApiModelProperty(value = "酒品名称-繁")
    private String wineNameCht;

    @ApiModelProperty(value = "酒品图片")
    private Long wineImgId;

    @ApiModelProperty(value = "酒品图片")
    private String wineImgUrl;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;


}