package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 美食列表查询参数
*/
@Data
@ApiModel(value = "美食列表查询参数")
public class FoodTypeListReqVO extends PageRequestVO {

    @ApiModelProperty("美食名称")
    private String foodTypeName;
}