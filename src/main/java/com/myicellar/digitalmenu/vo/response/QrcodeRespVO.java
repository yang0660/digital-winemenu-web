package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 二维码生成返回参数
 */
@Data
@ApiModel(value = "二维码生成返回参数")
public class QrcodeRespVO {

    @ApiModelProperty(value = "二维码图片地址")
    private String imageUrl;

}