package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 美食信息
 */
@Data
@ApiModel(value = "美食信息")
public class FoodRespVO {
    @ApiModelProperty(value = "美食ID")
    private Long foodId;

    @ApiModelProperty(value = "美食分类ID")
    private Long foodTypeId;

    @ApiModelProperty(value = "美食名称-英")
    private String foodNameEng;

    @ApiModelProperty(value = "美食名称-简")
    private String foodNameChs;

    @ApiModelProperty(value = "美食名称-繁")
    private String foodNameCht;

    @ApiModelProperty(value = "美食图片ID")
    private Long foodImgId;

    @ApiModelProperty(value = "美食价格")
    private BigDecimal price;

    @ApiModelProperty(value = "美食推荐")
    private Byte isRecommend;

    @ApiModelProperty(value = "美食启用")
    private Byte isEnabled;

    @ApiModelProperty(value = "创建者")
    private Long createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "更新者")
    private Long updatedBy;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "美食描述-英")
    private String notePlainEng;

    @ApiModelProperty(value = "美食描述-简")
    private String notePlainChs;

    @ApiModelProperty(value = "美食描述-繁")
    private String notePlainCht;


}