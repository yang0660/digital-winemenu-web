package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * 酒庄详情参数
 */
@Data
@ApiModel(value = "酒庄详情参数")
public class WineryDetailReqVO {

    @ApiModelProperty(value = "酒庄ID")
    private Long wineryId;


}