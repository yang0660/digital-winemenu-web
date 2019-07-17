package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * 酒庄新增/修改参数
 */
@Data
@ApiModel(value = "酒庄信息")
public class WineryReqVO {

    @ApiModelProperty(value = "酒庄ID")
    private Long wineryId;

    @ApiModelProperty(value = "酒庄名称缩写")
    private String winerySeoName;

    @ApiModelProperty(value = "酒庄名称-英")
    private String wineryNameEng;

    @ApiModelProperty(value = "酒庄名称-简")
    private String wineryNameChs;

    @ApiModelProperty(value = "酒庄名称-繁")
    private String wineryNameCht;

    @ApiModelProperty(value = "酒庄LogoId")
    private Long logoImgId;

    @ApiModelProperty(value = "banner图片Id")
    private Long bannerImgId;

    @ApiModelProperty(value = "更新时间")
    private Date updatedAt;

    @ApiModelProperty(value = "更新者")
    private Long updatedBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdAt;

    @ApiModelProperty(value = "创建者")
    private Long createdBy;

    @ApiModelProperty(value = "酒庄描述-英")
    private String notePlainEng;

    @ApiModelProperty(value = "酒庄描述-简")
    private String notePlainChs;

    @ApiModelProperty(value = "酒庄描述-繁")
    private String notePlainCht;

    @ApiModelProperty(value = "酒庄网页地址")
    private String aboutUrl;

    @ApiModelProperty(value = "酒庄图片")
    private List<Long> wineryImgIds;


}