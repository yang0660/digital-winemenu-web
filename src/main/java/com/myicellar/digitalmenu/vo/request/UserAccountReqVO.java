package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
* 用户帐号表
*/
@Data
@ApiModel(value = "用户帐户说明")
public class UserAccountReqVO extends PageRequestVO {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户List")
    private List<Long> userIdList;

    @ApiModelProperty("用户名")
    private String userName;
    
    @ApiModelProperty("真实姓名")
    private String realName;
    
    @ApiModelProperty("手机号码")
    private String mobile;
    
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("部门")
    private String departmentName;
    
    @ApiModelProperty("密码加密因子")
    private String salt;
    
    @ApiModelProperty("用户类型：1、公司员工 2、供应商用户，3 经销商用户")
    private Byte userType;
    
    @ApiModelProperty("个人资料审核状态：0 初始状态， 1 待审核，2 审核通过，3 审核不通过")
    private Byte materialStatus;
    
    @ApiModelProperty("微信openid(APP专用)")
    private String wxOpenId;
    
    @ApiModelProperty("微信昵称(APP专用)")
    private String wxName;
    
    @ApiModelProperty("微信头像(APP专用)")
    private String wxHeadImage;
    
    @ApiModelProperty("创建人")
    private Long createUser;
    
    @ApiModelProperty("创建时间")
    private Date createTime;
    
    @ApiModelProperty("更新人")
    private Long updateUser;
    
    @ApiModelProperty("更新时间")
    private Date updateTime;
    
}