package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
* 员工信息表
*/
@Data
public class UserReqVO {

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("用户姓名")
    private String realName;

    @ApiModelProperty("固定电话")
    private String telephone;

    @ApiModelProperty("手机号码")
    private String mobile;
    
    @ApiModelProperty("性别(1、男 2、女)")
    private Byte gender;
    
    @ApiModelProperty("部门ID")
    private Long departmentId;
    
    @ApiModelProperty("工号")
    private String userCode;
    
    @ApiModelProperty("职位")
    private String position;
    
    @ApiModelProperty("邮箱地址")
    private String email;
    
    @ApiModelProperty("备注")
    private String remark;
    
    @ApiModelProperty("状态 1 开启，0 关闭")
    private Byte status;

    @ApiModelProperty("已选角色集合")
    private List<Long> roleIds;

    @ApiModelProperty("角色ID 1-销售文员、2-业务员、3-采购员、4-采购文员")
    private Long roleId;

    @ApiModelProperty("创建人")
    private Long createUser;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新人")
    private Long updateUser;

    @ApiModelProperty("更新时间")
    private Date updateTime;

}