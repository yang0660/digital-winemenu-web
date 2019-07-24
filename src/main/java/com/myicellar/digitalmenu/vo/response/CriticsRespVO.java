package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 评价信息
 */
@Data
@ApiModel(value = "评价信息")
public class CriticsRespVO {
    @ApiModelProperty(value = "评价ID")
    private Long wineCriticsId;

    @ApiModelProperty(value = "评论家/机构名称-英")
    private String criticsNameEng;

    @ApiModelProperty(value = "评论家/机构名称-繁")
    private String criticsNameCht;

    @ApiModelProperty(value = "评论家/机构名称-简")
    private String criticsNameChs;
}