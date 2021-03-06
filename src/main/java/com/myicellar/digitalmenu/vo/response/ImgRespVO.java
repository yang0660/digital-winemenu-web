package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 图片信息
 */
@Data
@ApiModel(value = "图片信息")
public class ImgRespVO {

    @ApiModelProperty(value = "图片ID")
    private Long imgId;

    @ApiModelProperty(value = "图库分类ID")
    private Long imgTypeId;

    @ApiModelProperty(value = "图片url-原图")
    private String imgUrl = "";

    @ApiModelProperty(value = "图片名称-英文")
    private String imgNameEng;
}