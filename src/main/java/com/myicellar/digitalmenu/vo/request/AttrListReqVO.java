package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 酒品属性列表查询参数
*/
@Data
@ApiModel(value = "酒品属性列表查询参数")
public class AttrListReqVO {

    @ApiModelProperty(value = "酒品属性类别 1 风格，2 口味，3 葡萄")
    private Byte attrCatgNo;
}