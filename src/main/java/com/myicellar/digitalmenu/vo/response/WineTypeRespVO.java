package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 酒品类型
 */
@Data
@ApiModel(value = "酒品类型")
public class WineTypeRespVO {
    @ApiModelProperty(value = "酒品类型ID")
    private Long wineTypeId;

    @ApiModelProperty(value = "酒品类型名称缩写")
    private String wineTypeSeoName;

    @ApiModelProperty(value = "酒品类型名称-英")
    private String wineTypeNameEng;

    @ApiModelProperty(value = "酒品类型名称-简")
    private String wineTypeNameChs;

    @ApiModelProperty(value = "酒品类型名称-繁")
    private String wineTypeNameCht;

    @ApiModelProperty(value = "排序")
    private Short micRank;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;


}