package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 发送短信验证码
*/
@Data
@ApiModel(value = "发送短信验证码")
public class SendSmsCodeReqVO {

    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty("操作类型： 1 注册，2 重置密码,3 注册绑定登录")
    private Byte type;

}