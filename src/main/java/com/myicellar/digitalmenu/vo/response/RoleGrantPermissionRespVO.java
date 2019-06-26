package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
* 权限表
*/
@Data
@ApiModel(value = "授权情况")
public class RoleGrantPermissionRespVO implements Serializable {

    /**
    * 菜单ID
    */
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;

    /**
    * 菜单名称
    */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    /**
     * 资源授权数据
     */
    @ApiModelProperty(value = "资源授权数据")
    private List<GrantPermissionRespVO> grantList;

    
}