package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 容量价格返回信息
 */
@Data
@ApiModel(value = "容量价格返回信息")
public class VolumPriceRespVO {

    @ApiModelProperty(value = "容量ID")
    private Long volumeTypeId;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;
}