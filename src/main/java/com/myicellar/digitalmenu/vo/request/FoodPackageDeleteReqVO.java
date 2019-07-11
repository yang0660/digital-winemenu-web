package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 美食-酒品关联信息
 */
@Data
@ApiModel(value = "美食-酒品关联信息")
public class FoodPackageDeleteReqVO extends PageRequestVO{
    @ApiModelProperty(value = "美食ID")
    private Long foodId;

    @ApiModelProperty(value = "商品ID")
    private Long packageId;

    @ApiModelProperty(value = "创建者")
    private Long createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "更新者")
    private Long updatedBy;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;


}