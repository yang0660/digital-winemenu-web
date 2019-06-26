package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* 用户角色表
*/
@Data
@ApiModel(value = "用户角色说明")
public class UserRoleReqVO extends PageRequestVO {

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