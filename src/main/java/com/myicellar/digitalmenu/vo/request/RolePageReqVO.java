package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 角色表
*/
@Data
@ApiModel(value = "角色信息")
public class RolePageReqVO extends PageRequestVO{

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
}