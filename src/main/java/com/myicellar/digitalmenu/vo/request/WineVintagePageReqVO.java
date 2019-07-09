package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 酒品年份
 */
@Data
@ApiModel(value = "酒品年份")
public class WineVintagePageReqVO extends PageRequestVO{
    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "年份标签")
    private Long vintageTag;

    @ApiModelProperty(value = "年份名称")
    private String vintageName;

    @ApiModelProperty(value = "酒精含量")
    private Long alcoholBps;

    @ApiModelProperty(value = "酒品图片ID")
    private Long wineImgId;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "描述-英")
    private String notePlainEng;

    @ApiModelProperty(value = "描述-简")
    private String notePlainChs;

    @ApiModelProperty(value = "描述-繁")
    private String notePlainCht;


}