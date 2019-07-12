package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 二维码生成请求参数
*/
@Data
@ApiModel(value = "二维码生成请求参数")
public class QrcodeReqVO{

    @ApiModelProperty(value = "二维码内容")
    private String content;

}