package com.myicellar.digitalmenu.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
* 组织架构表
*/
@Data
@ApiModel(value = "更新部门信息参数")
public class DepartmentUpdateReqVO {

    /**
    * 部门ID
    */
    @ApiModelProperty(value = "部门ID")
    @NotEmpty(message = "部门ID不能为空")
    private Long departmentId;
    
    /**
    * 部门名称
    */
    @ApiModelProperty(value = "部门名称")
    private String departmentName;
    
    /**
    * 部门编号
    */
    @ApiModelProperty(value = "部门编号")
    private String departmentCode;

    /**
     * 经理用户ID集合
     */
    @ApiModelProperty(value = "经理用户ID集合")
    private List<Long> managerIds;

    /**
     * 副经理用户ID集合
     */
    @ApiModelProperty(value = "副经理用户ID集合")
    private List<Long> assistManagerIds;

    /**
     * 经理用户ID集合
     */
    @ApiModelProperty(value = "主管用户ID集合")
    private List<Long> masterIds;

    /**
     * 副经理用户ID集合
     */
    @ApiModelProperty(value = "副主管用户ID集合")
    private List<Long> assistMasterIds;

    /**
     * 更新类型
     */
    @ApiModelProperty(value = "更新类型：1 名称，2 编号，3 主管")
    @NotEmpty(message = "更新类型不能为空")
    private Byte type;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    private Long updateUser;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}