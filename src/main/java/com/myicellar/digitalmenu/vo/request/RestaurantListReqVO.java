package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* 餐厅列表查询参数
*/
@Data
@ApiModel(value = "餐厅列表查询参数")
public class RestaurantListReqVO extends PageRequestVO {

    @ApiModelProperty("餐厅名称")
    private String restaurantName;
}