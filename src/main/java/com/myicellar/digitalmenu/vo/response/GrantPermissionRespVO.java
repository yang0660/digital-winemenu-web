package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
* 权限表
*/
@Data
@ApiModel(value = "资源授权数据")
public class GrantPermissionRespVO implements Serializable {

    /**
    * 权限ID
    */
    @ApiModelProperty(value = "权限ID")
    private Long permissionId;
    
    /**
    * 资源名称
    */
    @ApiModelProperty(value = "资源名称")
    private String permissionName;

    /**
     * 是否已授权: 0 否，1 是
     */
    @ApiModelProperty(value = "是否已授权: 0 否，1 是")
    private Byte grantedFlag;

    
}