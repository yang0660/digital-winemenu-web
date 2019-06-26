package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* 登录记录表
*/
@Data
@ApiModel("登录记录表")
public class LoginRecordRespVO implements Serializable {

    /**
    * 主键ID
    */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    
    /**
    * 用户ID
    */
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    
    /**
    * 用户名称
    */
    @ApiModelProperty(value = "用户名称")
    private String userName;
    
    /**
    * 角色名称
    */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    
    /**
    * 登录时间
    */
    @ApiModelProperty(value = "登录时间")
    private Date loginTime;
    
    /**
    * 登录IP
    */
    @ApiModelProperty(value = "登录IP")
    private String loginIp;
    
    /**
    * 类型：1.登录、2.退出
    */
    @ApiModelProperty("类型：1.登录、2.退出")
    private Byte type;
    
}