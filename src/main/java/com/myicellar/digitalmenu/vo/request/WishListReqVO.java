package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* WISHLIST列表查询参数
*/
@Data
@ApiModel(value = "WISHLIST列表查询参数")
public class WishListReqVO {

    @ApiModelProperty(value = "酒品ID集合")
    private List<Long> productIds;
}