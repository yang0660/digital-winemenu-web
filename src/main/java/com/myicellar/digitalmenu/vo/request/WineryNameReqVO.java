package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 酒庄名称
 */
@Data
@ApiModel(value = "酒庄名称")
public class WineryNameReqVO extends PageRequestVO{

    @ApiModelProperty(value = "酒庄名称-英")
    private String wineryNameEng;
//
//    @ApiModelProperty(value = "酒庄名称-简")
//    private String wineryNameChs;
//
//    @ApiModelProperty(value = "酒庄名称-繁")
//    private String wineryNameCht;


}