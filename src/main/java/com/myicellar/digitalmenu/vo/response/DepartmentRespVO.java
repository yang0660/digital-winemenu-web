package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 组织架构表
 */
@Data
@ApiModel(value = "部门详情")
public class DepartmentRespVO implements Serializable {

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    private Long departmentId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String departmentName;

    /**
     * 上级部门ID
     */
    @ApiModelProperty(value = "上级部门ID")
    private Long parentId;

    /**
     * 部门编号
     */
    @ApiModelProperty(value = "部门编号")
    private String departmentCode;

    /**
     * 排序编号
     */
    @ApiModelProperty(value = "排序编号")
    private Integer sort;

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
     * 主管用户ID集合
     */
    @ApiModelProperty(value = "主管用户ID集合")
    private List<Long> masterIds;

    /**
     * 副主管用户ID集合
     */
    @ApiModelProperty(value = "副主管用户ID集合")
    private List<Long> assistMasterIds;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private Long createUser;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

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