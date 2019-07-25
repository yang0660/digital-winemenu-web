package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 酒品风格/口味/葡萄等属性
 */
@Data
@ApiModel(value = "酒品风格/口味/葡萄等属性")
public class AttrRespVO {
    @ApiModelProperty(value = "配料ID")
    private Long attrId;

    @ApiModelProperty(value = "口味ID")
    private Long attrCatgId;

    @ApiModelProperty(value = "配料名称-英")
    private String attrNameEng;

    @ApiModelProperty(value = "配料名称-简")
    private String attrNameChs;

    @ApiModelProperty(value = "配料名称-繁")
    private String attrNameCht;
}