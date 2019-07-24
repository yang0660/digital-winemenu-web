package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 图库分类新增/修改参数
 */
@Data
@ApiModel(value = "图库分类新增/修改参数")
public class ImgTypeReqVO {

    @ApiModelProperty(value = "图库分类ID(新增时，不需要传)")
    private Long imgTypeId;

    @ApiModelProperty(value = "图库分类名称-英文")
    private String imgTypeNameEng;

    @ApiModelProperty(value = "图库分类名称-简体中文")
    private String imgTypeNameChs;

    @ApiModelProperty(value = "图库分类名称-繁体中文")
    private String imgTypeNameCht;
}