package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 酒品价格信息
 */
@Data
@ApiModel(value = "酒品价格信息")
public class ProductPriceRespVO {

    @ApiModelProperty(value = "productId")
    private Long productId;

    @ApiModelProperty(value = "最低瓶装价格")
    private BigDecimal bottlePrice = new BigDecimal(0.00);

    @ApiModelProperty(value = "最低杯装价格")
    private BigDecimal glassPrice =  new BigDecimal(0.00);

}