package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 餐厅列表返回参数
 */
@Data
@ApiModel(value = "餐厅列表返回参数")
public class RestaurantListRespVO {

    @ApiModelProperty(value = "餐厅ID")
    private Long restaurantId;

    @ApiModelProperty(value = "餐厅名称-简")
    private String restaurantNameChs;

    @ApiModelProperty(value = "餐厅名称-繁")
    private String restaurantNameCht;

    @ApiModelProperty(value = "餐厅名称-英文")
    private String restaurantNameEng;

    @ApiModelProperty(value = "餐厅LOGO图片")
    private String logoUrl;

    @ApiModelProperty(value = "餐厅广告语")
    private String slogan;

    @ApiModelProperty(value = "餐厅海报图片")
    private String posterUrl;

    @ApiModelProperty(value = "状态")
    private Byte status;

    @ApiModelProperty(value = "创建人")
    private Long createUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "修改人")
    private Long updateUser;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}