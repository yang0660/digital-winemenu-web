package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 酒庄名称
 */
@Data
@ApiModel(value = "酒庄名称")
public class WineryNameReqVO {

    @ApiModelProperty(value = "酒庄名称-英")
    private String wineryNameEng;

}