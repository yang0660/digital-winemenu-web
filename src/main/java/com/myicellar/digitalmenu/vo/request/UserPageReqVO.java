package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* 员工信息表
*/
@Data
@ApiModel(value = "用户分页查询参数")
public class UserPageReqVO extends PageRequestVO {

    /**
    * 用户ID
    */
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    
    /**
    * 用户名
    */
    @ApiModelProperty(value = "userName")
    private String userName;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String realName;
    
    /**
    * 固定电话
    */
    @ApiModelProperty(value = "固定电话")
    private String telephone;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String mobile;
    
    /**
    * 性别(1、男 2、女)
    */
    @ApiModelProperty(value = "性别(1、男 2、女)")
    private Byte gender;
    
    /**
    * 部门ID
    */
    @ApiModelProperty(value = "部门ID")
    private Long departmentId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private Long departmentName;
    
    /**
    * 工号
    */
    @ApiModelProperty(value = "工号")
    private String userCode;
    
    /**
    * 职位
    */
    @ApiModelProperty(value = "职位")
    private String position;
    
    /**
    * 邮箱地址
    */
    @ApiModelProperty(value = "邮箱地址")
    private String email;
    
    /**
    * 备注
    */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /**
    * 状态 1 开启，0 关闭
    */
    @ApiModelProperty(value = "状态 1 开启，0 关闭")
    private Byte status;

    /**
     * 用户类型 ：1、公司员工 2、供应商用户，3 经销商用户
     */
    @ApiModelProperty(value = "用户类型 ：1、公司员工 2、供应商用户，3 经销商用户")
    private Byte userType;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
    
    /**
    * 创建人
    */
    private Long createUser;
    
    /**
    * 创建时间
    */
    private Date createTime;
    
    /**
    * 更新人
    */
    private Long updateUser;
    
    /**
    * 更新时间
    */
    private Date updateTime;
    
}