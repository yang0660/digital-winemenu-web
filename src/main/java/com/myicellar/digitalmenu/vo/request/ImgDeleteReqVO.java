package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 图片删除参数
 */
@Data
@ApiModel(value = "图片删除参数")
public class ImgDeleteReqVO {

    @ApiModelProperty(value = "图片ID")
    private Long imgId;
}