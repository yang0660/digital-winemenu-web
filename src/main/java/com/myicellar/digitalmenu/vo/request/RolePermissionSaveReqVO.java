package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
* 角色权限表
*/
@Data
@ApiModel(value = "保存角色授权数据")
public class RolePermissionSaveReqVO {

    /**
    * 角色ID
    */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
    
    /**
    * 已授权的资源ID集合
    */
    @ApiModelProperty(value = "已授权的资源ID集合")
    private List<Long> grantedList;

    /**
     * 未授权的资源ID集合
     */
    @ApiModelProperty(value = "已授权的资源ID集合")
    private List<Long> unGrantedList;
    
}