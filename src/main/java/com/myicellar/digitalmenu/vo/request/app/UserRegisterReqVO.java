package com.myicellar.digitalmenu.vo.request.app;

import com.myicellar.digitalmenu.vo.request.PageRequestVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* APP注册参数
*/
@Data
@ApiModel(value = "APP注册参数")
public class UserRegisterReqVO extends PageRequestVO {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("手机号码")
    private String mobile;

    @ApiModelProperty("短信验证码")
    private String smsCode;
}