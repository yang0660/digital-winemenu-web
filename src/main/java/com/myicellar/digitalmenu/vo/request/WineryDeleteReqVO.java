package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 酒庄删除信息
*/
@Data
@ApiModel(value = "酒庄删除信息")
public class WineryDeleteReqVO {

    @ApiModelProperty(value = "酒庄ID")
    private Long wineryId;
}