package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 年份信息
 */
@Data
@ApiModel(value = "年份信息")
public class VintageRespVO {

    @ApiModelProperty(value = "年份标签")
    private Long vintageTag;

    @ApiModelProperty(value = "年份描述")
    private String vintageName;
}