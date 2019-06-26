package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* 用户操作记录表
*/
@Data
public class OperationRecordRespVO implements Serializable {

    /**
    * 主键
    */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    
    /**
    * 用户ID
    */
    @ApiModelProperty(value = "用户ID")
    private Long userId;
    
    /**
    * 用户名称
    */
    @ApiModelProperty(value = "用户名称")
    private String userName;
    
    /**
    * 角色名称
    */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    
    /**
    * 操作描述
    */
    @ApiModelProperty(value = "操作描述")
    private String operationType;
    
    /**
    * 操作时间
    */
    @ApiModelProperty(value = "操作时间")
    private Date operationTime;
    
    /**
    * IP地址
    */
    @ApiModelProperty(value = "IP地址")
    private String ip;
    
}