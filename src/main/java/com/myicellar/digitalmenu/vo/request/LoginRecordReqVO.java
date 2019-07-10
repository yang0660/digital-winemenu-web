package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* 登录日志
*/
@Data
@ApiModel(value = "登录日志")
public class LoginRecordReqVO extends PageRequestVO {

    /**
    * 主键
    */
    @ApiModelProperty(value = "主键")
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
    @ApiModelProperty(value = "类型：1.登录、2.退出")
    private Byte type;
    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private  Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private  Date endTime;
    
}