package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 美食信息
*/
@Data
@ApiModel(value = "美食信息")
public class FoodDeleteReqVO {

    @ApiModelProperty(value = "美食ID")
    private Long foodId;
}