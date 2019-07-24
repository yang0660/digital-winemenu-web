package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 图片批量删除参数
 */
@Data
@ApiModel(value = "图片批量删除参数")
public class ImgBatchDeleteReqVO {

    @ApiModelProperty(value = "图片ID集合")
    private List<Long> imgIds;
}