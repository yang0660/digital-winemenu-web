package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.Role;
import com.myicellar.digitalmenu.enums.RoleEnum;
import com.myicellar.digitalmenu.service.RolePermissionService;
import com.myicellar.digitalmenu.service.RoleService;
import com.myicellar.digitalmenu.service.UserRoleService;
import com.myicellar.digitalmenu.shiro.SysLog;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.PrincipalContext;
import com.myicellar.digitalmenu.vo.request.RolePageReqVO;
import com.myicellar.digitalmenu.vo.request.RolePermissionSaveReqVO;
import com.myicellar.digitalmenu.vo.request.RoleReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.RoleGrantPermissionRespVO;
import com.myicellar.digitalmenu.vo.response.RoleRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manage/role")
@Api(tags = "角色管理", description = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 角色列表查询(分页)
     * @param reqVO
     * @return
     */
    @PostMapping("queryListByCondition")
    @SysLog(operation = "角色列表查询")
    @ApiOperation(value = "角色列表查询(分页，支持模糊匹配)")
    @RequiresPermissions(value = {"role:queryPage"})
    public ResultVO<PageResponseVO<RoleRespVO>> queryList(@RequestBody RolePageReqVO reqVO) {
        PageResponseVO<Role> page = roleService.queryRoleList(reqVO);
        return ResultVO.success(ConvertUtils.convertPage(page, RoleRespVO.class));
    }

    /**
     * 根据ID查询角色信息
     * @param reqVO
     * @return
     */
    @PostMapping("queryById")
    @ApiOperation("根据ID查询角色信息")
    @SysLog(operation = "角色管理-详情")
    @RequiresPermissions(value = {"role:edit"})
    public ResultVO<RoleRespVO> queryById(@RequestBody RoleReqVO reqVO) {
        Role role = roleService.selectByPrimaryKey(reqVO.getRoleId());
        return ResultVO.success(ConvertUtils.convert(role, RoleRespVO.class));
    }

    /**
     * 添加新角色
     * @param reqVO
     * @return
     */
    @PostMapping("addNew")
    @ApiOperation(value = "添加新角色")
    @SysLog(operation = "角色管理-新增")
    @RequiresPermissions(value = {"role:add"})
    public ResultVO addNew(@RequestBody RoleReqVO reqVO) {
        Role role = ConvertUtils.convert(reqVO, Role.class);
        Long currentUserId = PrincipalContext.getCurrentUserId();
        role.setCreateUser(currentUserId);
        role.setUpdateUser(currentUserId);
        ResultVO result = roleService.addNew(role);
        return result;
    }

    /**
     * 删除角色
     * @param reqVO
     * @return
     */
    @PostMapping("deleteById")
    @ApiOperation(value = "删除角色")
    @SysLog(operation = "角色管理-删除")
    @RequiresPermissions(value = {"role:delete"})
    public ResultVO deleteById(@RequestBody RoleReqVO reqVO) {
        RoleEnum roleEnum = RoleEnum.enumOf(reqVO.getRoleId());
        if(roleEnum!=null){
            return ResultVO.validError("该角色为内置角色，不能删除!");
        }

        int userCount = userRoleService.queryCountByRoleId(reqVO.getRoleId());
        if(userCount>0){
            return ResultVO.validError("存在拥有该角色的用户，不能删除!");
        }

        int count = roleService.deleteByPrimaryKey(reqVO.getRoleId());
        if(count==0){
            return ResultVO.validError("删除失败!");
        }
        return ResultVO.success("删除成功!");
    }

    /**
     * 角色权限查询
     * @param reqVO
     * @return
     */
    @PostMapping("queryPermissionsById")
    @ApiOperation("角色权限查询")
    @SysLog(operation = "角色管理-权限查询")
    @RequiresPermissions(value = {"role:permAllot"})
    public ResultVO<List<RoleGrantPermissionRespVO>> queryPermissionsById(@RequestBody RoleReqVO reqVO) {
        List<RoleGrantPermissionRespVO> grantPermissions = rolePermissionService.queryPermissionsByRoleId(reqVO.getRoleId());
        return ResultVO.success(grantPermissions);
    }

    /**
     * 角色权限查询
     * @param reqVO
     * @return
     */
    @PostMapping("savePermissions")
    @ApiOperation("角色权限查询")
    @SysLog(operation = "角色管理-权限分配")
    @RequiresPermissions(value = {"role:permAllot"})
    public ResultVO savePermissions(@RequestBody RolePermissionSaveReqVO reqVO) {
        ResultVO result = rolePermissionService.savePermissions(reqVO);
        return result;
    }
}
