package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 美食详情参数
*/
@Data
@ApiModel(value = "美食详情参数")
public class FoodDetailReqVO {

    @ApiModelProperty(value = "美食ID")
    private Long foodId;

}