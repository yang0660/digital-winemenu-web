package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 忘记密码-重置密码
*/
@Data
@ApiModel(value = "忘记密码-重置密码")
public class UpdatePasswordForgetReqVO {

    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty("验证码")
    private String smsCode;

    @ApiModelProperty("新密码")
    private String password;
}