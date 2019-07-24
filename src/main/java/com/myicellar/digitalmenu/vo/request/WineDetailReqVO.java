package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 酒品详情查询参数
 */
@Data
@ApiModel(value = "酒品详情查询参数")
public class WineDetailReqVO extends PageRequestVO {

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

}