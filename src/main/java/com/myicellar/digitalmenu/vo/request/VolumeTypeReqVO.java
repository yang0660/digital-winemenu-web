package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
/**
 * 容量
 */
@Data
@ApiModel(value = "容量")
public class VolumeTypeReqVO {
    @ApiModelProperty(value = "容量ID")
    private Long volumeTypeId;

    @ApiModelProperty(value = "容量-毫升")
    private Long volInMl;

    @ApiModelProperty(value = "排序")
    private Integer micRank;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "容量代码")
    private String volumeTypeCode;

    @ApiModelProperty(value = "容量名称")
    private String typeNameEng;


}