package com.myicellar.digitalmenu.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 菜单表
 */
@Data
@ApiModel(value = "菜单详情")
public class MenuTreeRespVO implements Serializable {

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
     * 上级菜单ID
     */
    @ApiModelProperty(value = "上级菜单ID")
    private Long parentId;

    /**
     * 菜单url
     */
    @ApiModelProperty(value = "菜单url")
    private String menuUrl;

    /**
     * 排序编号
     */
    @ApiModelProperty(value = "排序编号")
    private Integer sort;

    /**
     * 菜单层级
     */
    @ApiModelProperty(value = "菜单层级")
    private Long level;

    /**
     * 是否叶子节点
     */
    @ApiModelProperty(value = "是否叶子节点 0 否，1 是")
    private Byte isLeaf;

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

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "下级部门集合")
    private List<MenuTreeRespVO> children;

}