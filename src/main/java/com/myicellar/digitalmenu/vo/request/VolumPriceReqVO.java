package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 容量价格信息
 */
@Data
@ApiModel(value = "容量价格信息")
public class VolumPriceReqVO {

    @ApiModelProperty(value = "容量ID")
    private Long volumeTypeId;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;
}