package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* 组织架构表
*/
@Data
@ApiModel(value = "组织架构删除/详情参数")
public class DepartmentDqVO {

    /**
    * 部门ID
    */
    @ApiModelProperty(value = "部门ID")
    @NotNull(message = "部门ID不能为空")
    private Long departmentId;
    
}