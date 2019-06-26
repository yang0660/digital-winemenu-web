package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* 员工信息表
*/
@Data
public class UserRespVO implements Serializable {

    @ApiModelProperty("用户ID")
    private Long userId;
    
    @ApiModelProperty("固定电话")
    private String telephone;
    
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
    
    @ApiModelProperty("创建人")
    private Long createUser;
    
    @ApiModelProperty("创建时间")
    private Date createTime;
    
    @ApiModelProperty("更新人")
    private Long updateUser;
    
    @ApiModelProperty("更新时间")
    private Date updateTime;
    
}