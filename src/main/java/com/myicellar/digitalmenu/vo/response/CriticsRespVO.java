package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 评价信息
 */
@Data
@ApiModel(value = "评价信息")
public class CriticsRespVO {
    @ApiModelProperty(value = "评价ID")
    private Long wineCriticsId;

    @ApiModelProperty(value = "评价名称缩写")
    private String criticsSeoName;

    @ApiModelProperty(value = "评价名称-英")
    private String criticsNameEng;

    @ApiModelProperty(value = "评价名称-繁")
    private String criticsNameCht;

    @ApiModelProperty(value = "评价名称-简")
    private String criticsNameChs;

    @ApiModelProperty(value = "排序")
    private Short micRank;

    @ApiModelProperty(value = "最高得分")
    private Short scoreMax;

    @ApiModelProperty(value = "分数单位")
    private Short scoreFactor;

    @ApiModelProperty(value = "相关网址")
    private String aboutUrl;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;


}