package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 产品新增/修改参数
 */
@Data
@ApiModel(value = "产品新增/修改参数")
public class ProductReqVO {
    @ApiModelProperty(value = "产品ID")
    private Long productId;
}