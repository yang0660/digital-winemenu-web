package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 酒品-配料/口味关联信息
 */
@Data
@ApiModel(value = "酒品-配料/口味关联信息")
public class WineAttrPageReqVO extends PageRequestVO{
    @ApiModelProperty(value = "配料ID")
    private Long attrId;

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "排序")
    private Short micRank;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;


}