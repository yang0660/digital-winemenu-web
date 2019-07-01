package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 美食信息
 */
@Data
@ApiModel(value = "美食信息")
public class FoodTypeReqVO {
    @ApiModelProperty(value = "美食ID")
    private Long foodTypeId;

    @ApiModelProperty(value = "餐厅ID")
    private Long restaurantId;

    @ApiModelProperty(value = "美食名称-简")
    private String foodTypeNameChs;

    @ApiModelProperty(value = "美食名称-繁")
    private String foodTypeNameCht;

    @ApiModelProperty(value = "美食名称-英文")
    private String foodTypeNameEng;

    @ApiModelProperty(value = "创建人")
    private Long createUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新人")
    private Long updateUser;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}