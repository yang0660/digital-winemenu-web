package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 评价信息
 */
@Data
@ApiModel(value = "评价信息")
public class CriticsDeleteReqVO {
    @ApiModelProperty(value = "评价ID")
    private Long criticsId;


}