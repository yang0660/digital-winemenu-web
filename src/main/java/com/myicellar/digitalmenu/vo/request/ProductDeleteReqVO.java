package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 产品删除参数
 */
@Data
@ApiModel(value = "产品删除参数")
public class ProductDeleteReqVO {
    @ApiModelProperty(value = "产品ID")
    private Long productId;


}