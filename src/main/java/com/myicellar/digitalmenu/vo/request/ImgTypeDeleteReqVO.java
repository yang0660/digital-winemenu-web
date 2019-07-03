package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 图库分类信息
*/
@Data
@ApiModel(value = "图库分类信息")
public class ImgTypeDeleteReqVO {

    @ApiModelProperty(value = "图库分类ID")
    private Long imgTypeId;
}