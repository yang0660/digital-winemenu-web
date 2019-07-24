package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 酒庄相关信息查询参数-分页
 */
@Data
@ApiModel(value = "酒庄相关信息查询参数-分页")
public class WineryPageReqVO extends PageRequestVO {

    @ApiModelProperty(value = "酒庄名称-英")
    private String wineryNameEng;
}