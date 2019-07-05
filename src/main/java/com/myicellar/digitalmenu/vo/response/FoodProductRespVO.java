package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 美食-产品关联信息
 */
@Data
@ApiModel(value = "美食-产品关联信息")
public class FoodProductRespVO {
    @ApiModelProperty(value = "美食ID")
    private Long foodId;

    @ApiModelProperty(value = "产品ID")
    private Long productId;

    @ApiModelProperty(value = "创建者")
    private Long createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "更新者")
    private Long updatedBy;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;


}