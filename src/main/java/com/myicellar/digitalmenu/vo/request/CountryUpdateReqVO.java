package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 国家信息
 */
@Data
@ApiModel(value = "国家信息")
public class CountryUpdateReqVO {
    @ApiModelProperty(value = "国家ID")
    private Long countryId;

    @ApiModelProperty(value = "国家代码")
    private String countryCode;

    @ApiModelProperty(value = "国家名称缩写")
    private String countrySeoName;

    @ApiModelProperty(value = "国家名称-英")
    private String countryNameEng;

    @ApiModelProperty(value = "国家名称-简")
    private String countryNameChs;

    @ApiModelProperty(value = "国家名称-繁")
    private String countryNameCht;

    @ApiModelProperty(value = "国家图片")
    private Long imgId;

    @ApiModelProperty(value = "排序")
    private Short micRank;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;


}