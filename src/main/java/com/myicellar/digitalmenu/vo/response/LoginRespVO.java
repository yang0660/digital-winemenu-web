package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* 用户帐号表
*/
@Data
public class LoginRespVO implements Serializable {

    /**
    * 用户ID
    */
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    
    /**
    * 用户名
    */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String realName;
    
    /**
    * 手机号码
    */
    @ApiModelProperty(value = "手机号码")
    private String mobile;

    /**
    * 用户类型：1、公司员工 2、供应商用户，3 经销商用户
    */
    @ApiModelProperty(value = "用户类型：1、公司员工 2、供应商用户，3 经销商用户")
    private Byte userType;
    
    /**
    * 个人资料审核状态：0 初始状态， 1 待审核，2 审核通过，3 审核不通过
    */
    @ApiModelProperty(value = "个人资料审核状态：0 初始状态， 1 待审核，2 审核通过，3 审核不通过")
    private Byte materialStatus;

    /**
     * 登录token
     */
    @ApiModelProperty(value = "登录token")
    private String token;
}