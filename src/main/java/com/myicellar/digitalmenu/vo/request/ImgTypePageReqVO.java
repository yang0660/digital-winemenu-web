package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 图库分类查询参数-分页
*/
@Data
@ApiModel(value = "图库分类查询参数-分页")
public class ImgTypePageReqVO extends PageRequestVO{

    @ApiModelProperty(value = "图库分类名称-英文")
    private String imgTypeNameEng;
}