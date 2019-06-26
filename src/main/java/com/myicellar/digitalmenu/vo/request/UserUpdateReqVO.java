package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 员工信息表
*/
@Data
@ApiModel(value = "修改个人信息")
public class UserUpdateReqVO {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("固定电话")
    private String telephone;

    @ApiModelProperty("手机号码")
    private String mobile;
    
    @ApiModelProperty("性别(1、男 2、女)")
    private Byte gender;
    
    @ApiModelProperty("工号")
    private String userCode;
    
    @ApiModelProperty("邮箱地址")
    private String email;
}