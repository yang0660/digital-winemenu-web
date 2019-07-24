package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 酒品报表数据
 */
@Data
@ApiModel(value = "酒品报表数据")
public class ProductReportRespVO {

    @ApiModelProperty(value = "供应商ID")
    private Long supplierId;

    @ApiModelProperty(value = "供应商名称")
    private String supplierName;

    @ApiModelProperty(value = "productId")
    private Long productId;

    @ApiModelProperty(value = "酒品ID")
    private Long wineId;

    @ApiModelProperty(value = "酒品名称")
    private String wineName;

    @ApiModelProperty(value = "年份描述")
    private String vintageName;

    @ApiModelProperty(value = "日期")
    private String thisDate;

    @ApiModelProperty(value = "数量")
    private Integer num;
}