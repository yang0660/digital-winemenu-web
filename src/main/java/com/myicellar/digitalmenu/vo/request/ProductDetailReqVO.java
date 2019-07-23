package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * package详情请求信息
 */
@Data
@ApiModel(value = "package详情请求信息")
public class ProductDetailReqVO {

    @ApiModelProperty(value = "productId")
    private Long productId;
}