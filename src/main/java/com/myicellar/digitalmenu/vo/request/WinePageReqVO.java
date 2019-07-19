package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 酒品相关信息查询参数-分页
 */
@Data
@ApiModel(value = "酒品相关信息查询参数-分页")
public class WinePageReqVO extends PageRequestVO{
    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "酒品类型ID")
    private Long wineTypeId;

    @ApiModelProperty(value = "酒品产地ID")
    private Long wineOriginId;

    @ApiModelProperty(value = "酒庄ID")
    private Long wineryId;

    @ApiModelProperty(value = "酒品名称-英")
    private String wineNameEng;
}