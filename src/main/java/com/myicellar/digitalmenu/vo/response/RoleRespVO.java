package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* 角色表
*/
@Data
public class RoleRespVO implements Serializable {

    /**
    * 角色ID
    */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
    
    /**
    * 角色名称
    */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    
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