package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 图片信息
*/
@Data
@ApiModel(value = "图片信息")
public class ImgDeleteReqVO {

    @ApiModelProperty(value = "图片ID")
    private Long imgId;
}