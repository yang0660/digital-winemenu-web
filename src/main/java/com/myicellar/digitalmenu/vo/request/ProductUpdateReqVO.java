package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品更新参数
 */
@Data
@ApiModel(value = "产品更新参数")
public class ProductUpdateReqVO {
    @ApiModelProperty(value = "产品ID")
    private Long productId;

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "年份标签")
    private String vintageTag;

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "价格")
    private BigDecimal regularPrice;

    @ApiModelProperty(value = "容量类型ID")
    private Long volumeTypeId;

    @ApiModelProperty(value = "杯装价格")
    private BigDecimal glassPrice;

    @ApiModelProperty(value = "杯装容量类型ID")
    private Long glassVolumeTypeId;

    @ApiModelProperty(value = "推荐")
    private Byte isRecommend;

    @ApiModelProperty(value = "启用")
    private Byte isEnabled;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "创建者")
    private Long createdBy;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "更新者")
    private Long updatedBy;


}