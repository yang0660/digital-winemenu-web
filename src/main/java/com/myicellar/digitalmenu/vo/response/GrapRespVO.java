package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "葡萄信息")
public class GrapRespVO {
    @ApiModelProperty(value = "葡萄属性ID")
    private Long grapId;

    @ApiModelProperty(value = "葡萄比例")
    private BigDecimal rate;
}