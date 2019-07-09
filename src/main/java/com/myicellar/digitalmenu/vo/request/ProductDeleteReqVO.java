package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 产品信息
 */
@Data
@ApiModel(value = "产品信息")
public class ProductDeleteReqVO {
    @ApiModelProperty(value = "产品ID")
    private Long productId;


}