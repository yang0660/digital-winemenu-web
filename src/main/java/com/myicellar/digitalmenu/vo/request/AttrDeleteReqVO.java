package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 酒品原材料配料口味删除信息
 */
@Data
@ApiModel(value = "酒品原材料配料口味删除信息")
public class AttrDeleteReqVO {
    @ApiModelProperty(value = "配料ID")
    private Long attrId;


}