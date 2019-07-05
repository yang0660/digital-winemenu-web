package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 美食-产品关联信息
 */
@Data
@ApiModel(value = "美食-产品关联信息")
public class FoodProductpageReqVO extends PageRequestVO{
    @ApiModelProperty(value = "美食ID")
    private Long foodId;

    @ApiModelProperty(value = "产品ID")
    private Long productId;


}