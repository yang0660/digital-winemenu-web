package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
* 员工信息表
*/
@Data
@ApiModel(value = "用户详情")
public class UserListRespVO implements Serializable {

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
    * 固定电话
    */
    @ApiModelProperty(value = "固定电话")
    private String telephone;

    /**
     * 移动电话电话
     */
    @ApiModelProperty(value = "移动电话电话")
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
     * 部门编号
     */
    @ApiModelProperty(value = "部门编号")
    private String departmentCode;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String departmentName;
    
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

    @ApiModelProperty("用户类型 2、供应商用户 3、经销商用户")
    private Byte userType;

    /**
     * 角色ID集合
     */
    @ApiModelProperty(value = "角色ID集合")
    private Set<Long> roleIds;

    /**
     * 角色名称集合
     */
    @ApiModelProperty(value = "角色名称集合")
    private Set<String> roleNames;

    /**
     * 是否已选
     */
    @ApiModelProperty(value = "是否已选: 0 否，1 是")
    private byte isSelected = 0;

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