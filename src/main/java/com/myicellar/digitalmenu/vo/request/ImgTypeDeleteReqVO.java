package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 图库分类删除参数
 */
@Data
@ApiModel(value = "图库分类删除参数")
public class ImgTypeDeleteReqVO {

    @ApiModelProperty(value = "图库分类ID")
    private Long imgTypeId;
}