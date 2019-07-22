package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "葡萄信息")
public class GrapReqVO {
    @ApiModelProperty(value = "葡萄属性ID")
    private Long grapId;

    @ApiModelProperty(value = "葡萄比例")
    private BigDecimal rate;
}