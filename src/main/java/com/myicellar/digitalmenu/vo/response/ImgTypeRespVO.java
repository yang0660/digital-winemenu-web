package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 图库分类信息
*/
@Data
@ApiModel(value = "图库分类信息")
public class ImgTypeRespVO {

    @ApiModelProperty(value = "图库分类ID")
    private Long imgTypeId;

    @ApiModelProperty(value = "图库分类名称-英文")
    private String imgTypeNameEng;

    @ApiModelProperty(value = "图库分类名称-简体中文")
    private String imgTypeNameChs;

    @ApiModelProperty(value = "图库分类名称-繁体中文")
    private String imgTypeNameCht;
}