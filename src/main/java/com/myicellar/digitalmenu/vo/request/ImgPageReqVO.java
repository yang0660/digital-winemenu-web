package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 图库查询参数-分页
 */
@Data
@ApiModel(value = "图库查询参数-分页")
public class ImgPageReqVO extends PageRequestVO {

    @ApiModelProperty(value = "图库分类ID")
    private Long imgTypeId;

    @ApiModelProperty(value = "图片名称-英文")
    private String imgNameEng;
}