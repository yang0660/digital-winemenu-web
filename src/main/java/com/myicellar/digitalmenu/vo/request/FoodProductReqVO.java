package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 美食关联酒品参数
 */
@Data
@ApiModel(value = "美食关联酒品参数")
public class FoodProductReqVO {

    @ApiModelProperty(value = "美食ID")
    private Long foodId;

    @ApiModelProperty(value = "productId集合")
    private List<Long> productIds;
}