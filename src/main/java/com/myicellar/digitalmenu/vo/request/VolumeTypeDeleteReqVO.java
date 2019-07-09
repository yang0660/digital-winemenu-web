package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 容量删除信息
*/
@Data
@ApiModel(value = "容量删除信息")
public class VolumeTypeDeleteReqVO {

    @ApiModelProperty(value = "volumeTypeId")
    private Long volumeTypeId;
}