package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 酒品原材料配料口味信息
 */
@Data
@ApiModel(value = "酒品原材料配料口味信息")
public class AttrUpdateReqVO {
    @ApiModelProperty(value = "配料ID")
    private Long attrId;

    @ApiModelProperty(value = "分类ID")
    private Long attrCatgId;

    @ApiModelProperty(value = "口味名称")
    private String attrCatgName;

    @ApiModelProperty(value = "配料名称缩写")
    private String attrSeoName;

    @ApiModelProperty(value = "配料名称-英")
    private String attrNameEng;

    @ApiModelProperty(value = "配料名称-简")
    private String attrNameChs;

    @ApiModelProperty(value = "配料名称-繁")
    private String attrNameCht;

    @ApiModelProperty(value = "排序")
    private Short micRank;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;


}