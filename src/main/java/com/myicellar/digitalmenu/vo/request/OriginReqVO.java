package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 产地信息
 */
@Data
@ApiModel(value = "产地信息")
public class OriginReqVO {
    @ApiModelProperty(value = "产地ID")
    private Long wineOriginId;

    @ApiModelProperty(value = "产品名称缩写")
    private String regionSeoName;

    @ApiModelProperty(value = "产品名称-英")
    private String regionNameEng;

    @ApiModelProperty(value = "产品名称-简")
    private String regionNameChs;

    @ApiModelProperty(value = "产品名称-繁")
    private String regionNameCht;

    @ApiModelProperty(value = "父产地ID")
    private Long parentOriginId;

    @ApiModelProperty(value = "国家ID")
    private Long countryId;

    @ApiModelProperty(value = "排序")
    private Short micRank;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;


}