package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 酒品相关信息查询参数
 */
@Data
@ApiModel(value = "酒品相关信息查询参数")
public class WineReqVO {
    @ApiModelProperty(value = "酒品ID（新增不传）")
    private Long wineId;

    @ApiModelProperty(value = "酒品类型ID")
    private Long wineTypeId;

    @ApiModelProperty(value = "酒品产地ID")
    private Long wineOriginId;

    @ApiModelProperty(value = "酒庄ID")
    private Long wineryId;

    @ApiModelProperty(value = "酒品名称-英")
    private String wineNameEng;

    @ApiModelProperty(value = "酒品名称-简")
    private String wineNameChs;

    @ApiModelProperty(value = "酒品名称-繁")
    private String wineNameCht;

    @ApiModelProperty(value = "酒品图片")
    private Long wineImgId;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;


}