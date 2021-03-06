package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 产地筛选参数
 */
@Data
@ApiModel(value = "产地筛选参数")
public class OriginListReqVO {

    @ApiModelProperty(value = "国家ID")
    private Long countryId;

}