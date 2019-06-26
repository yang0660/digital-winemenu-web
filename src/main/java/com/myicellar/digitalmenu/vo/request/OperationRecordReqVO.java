package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
* 用户操作记录表
*/
@Data
@ApiModel(value = "用户操作记录说明")
public class OperationRecordReqVO extends PageRequestVO {

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
    /**
     * 开始时间
     */
     @ApiModelProperty(value = "开始时间")
     private  Date startTime;
    /**
     * 结束时间
     */
     @ApiModelProperty(value = "结束时间")
     private  Date endTime;
    
}