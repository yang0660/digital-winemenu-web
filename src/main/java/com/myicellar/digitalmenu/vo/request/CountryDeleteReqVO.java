package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 国家删除信息
 */
@Data
@ApiModel(value = "国家删除信息")
public class CountryDeleteReqVO {
    @ApiModelProperty(value = "国家ID")
    private Long countryId;


}