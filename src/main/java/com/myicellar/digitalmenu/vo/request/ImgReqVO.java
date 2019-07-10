package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 图片新增/修改参数
*/
@Data
@ApiModel(value = "图片新增/修改参数")
public class ImgReqVO {

    @ApiModelProperty(value = "图片ID(新增时，不需要传)")
    private Long imgId;

    @ApiModelProperty(value = "图库分类ID")
    private Long imgTypeId;

    @ApiModelProperty(value = "图片base64字符串")
    private String base64Str;

    @ApiModelProperty(value = "图片名称-英文")
    private String imgNameEng;

    @ApiModelProperty(value = "图片名称-简体中文")
    private String imgNameChs;

    @ApiModelProperty(value = "图片名称-繁体中文")
    private String imgNameCht;
}