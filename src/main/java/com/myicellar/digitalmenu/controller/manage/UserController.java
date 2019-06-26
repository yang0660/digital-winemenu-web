package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.service.MenuService;
import com.myicellar.digitalmenu.service.UserAccountService;
import com.myicellar.digitalmenu.service.UserService;
import com.myicellar.digitalmenu.shiro.SysLog;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.PrincipalContext;
import com.myicellar.digitalmenu.vo.request.*;
import com.myicellar.digitalmenu.vo.response.*;
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
@RequestMapping("/manage/user")
@Api(tags = "用户管理", description = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private MenuService menuService;

    /**
     * 用户列表查询(分页)
     * @param reqVO
     * @return
     */
    @PostMapping("queryListByCondition")
    @ApiOperation(value = "用户列表查询(分页，支持模糊匹配)")
    @SysLog(operation = "用户管理-列表查询")
    @RequiresPermissions(value = {"user:queryPage"})
    public ResultVO<PageResponseVO<UserListRespVO>> queryList(@RequestBody UserPageReqVO reqVO) {
        PageResponseVO<UserListRespVO> page = userService.queryUserList(reqVO);
        return ResultVO.success(page);
    }

    /**
     * 根据ID查询角色信息
     * @param reqVO
     * @return
     */
    @PostMapping("queryListByRoleId")
    @ApiOperation("根据RoleID查询用户信息-不分页")
    public ResultVO<List<UserAccountRespVO>> queryListByRoleId(@RequestBody UserReqVO reqVO) {
        List<UserAccountRespVO> user = ConvertUtils.convert(userAccountService.selectByRoleId(reqVO.getRoleId()), UserAccountRespVO.class);
        return ResultVO.success(user);
    }

    /**
     * 根据ID查询角色信息
     * @param reqVO
     * @return
     */
    @PostMapping("queryListByDepartmentIdAndRoleId")
    @ApiOperation("根据RoleID查询用户信息-不分页")
    public ResultVO<List<UserAccountRespVO>> queryListByDepartmentIdAndRoleId(@RequestBody UserReqVO reqVO) {
        List<UserAccountRespVO> user = ConvertUtils.convert(userAccountService.selectByRoleIdAndDepartmentId(reqVO.getRoleId(), reqVO.getDepartmentId()), UserAccountRespVO.class);
        return ResultVO.success(user);
    }


    /**
     * 根据ID查询角色信息
     * @param reqVO
     * @return
     */
    @PostMapping("queryById")
    @ApiOperation("根据ID查询用户信息")
    @SysLog(operation = "用户管理-详情")
    @RequiresPermissions(value = {"user:edit"})
    public ResultVO<UserListRespVO> queryById(@RequestBody UserReqVO reqVO) {
        UserPageReqVO condition = new UserPageReqVO();
        condition.setUserId(reqVO.getUserId());
        UserListRespVO user = userService.queryUserInfo(condition);
        return ResultVO.success(user);
    }

    /**
     * 添加新用户
     * @param reqVO
     * @return
     */
    @PostMapping("addNew")
    @ApiOperation(value = "添加新用户")
    @SysLog(operation = "用户管理-新增")
    @RequiresPermissions(value = {"user:add"})
    public ResultVO addNew(@RequestBody UserReqVO reqVO) {
        ResultVO result = userService.addNew(reqVO);
        return result;
    }

    /**
     * 修改用户信息
     * @param reqVO
     * @return
     */
    @PostMapping("update")
    @ApiOperation(value = "修改用户信息")
    @SysLog(operation = "用户管理-修改")
    @RequiresPermissions(value = {"user:edit"})
    public ResultVO update(@RequestBody UserReqVO reqVO) {
        ResultVO result = userService.update(reqVO);
        return result;
    }

    /**
     * 根据ID查询角色信息
     * @return
     */
    @PostMapping("queryUserInfo")
    @ApiOperation("查询用户详情信息")
    /*@SysLog(operation = "个人信息详情")*/
    public ResultVO<UserListRespVO> queryUserInfo() {
        Long currentUserId = PrincipalContext.getCurrentUserId();
        UserPageReqVO condition = new UserPageReqVO();
        condition.setUserId(currentUserId);
        UserListRespVO user = userService.queryUserInfo(condition);
        return ResultVO.success(user);
    }

    /**
     * 修改个人信息
     * @param reqVO
     * @return
     */
    @PostMapping("updateUserInfo")
    @ApiOperation(value = "修改个人信息")
    @SysLog(operation = "修改个人信息")
    public ResultVO update(@RequestBody UserUpdateReqVO reqVO) {
        ResultVO result = userService.updateUserInfo(reqVO);
        return result;
    }

    /**
     * 菜单树状列表查询
     * @return
     */
    @PostMapping("queryMenuTree")
    @ApiOperation(value = "菜单树状列表查询")
    /*@SysLog(operation = "菜单列表查询")*/
    public ResultVO<List<MenuTreeRespVO>> queryMenuTree() {
        List<MenuTreeRespVO> menuTree = menuService.queryTree();
        return ResultVO.success(menuTree);
    }

    /**
     * 修改密码
     * @param reqVO
     * @return
     */
    @PostMapping("updatePassword")
    @ApiOperation(value = "修改密码")
    @SysLog(operation = "修改密码")
    public ResultVO updatePassword(@RequestBody UpdatePasswordReqVO reqVO) {
        ResultVO result = userAccountService.updatePassword(reqVO);
        return result;
    }

    /**
     * 修改用户状态
     * @param reqVO
     * @return
     */
    @PostMapping("/updateStatus")
    @ApiOperation(value = "修改用户状态")
    @SysLog(operation = "用户管理-修改状态")
    @RequiresPermissions(value = {"user:edit"})
    public ResultVO updateStatus(@RequestBody UserReqVO reqVO) {
        ResultVO result = userService.updateStatus(reqVO);
        return result;
    }
    /**
     * 删除
     * @param reqVO
     * @return
     */
    @PostMapping("deleteById")
    @ApiOperation(value = "删除用户")
    @SysLog(operation = "用户管理-删除")
    @RequiresPermissions(value = {"user:delete"})
    public ResultVO deleteById(@RequestBody UserReqVO reqVO) {
        ResultVO result = userService.deleteById(reqVO.getUserId());
        return result;
    }
    @PostMapping("queryUserAccountInfo")
    @ApiOperation(value = "筛选账户信息")
    @SysLog(operation = "筛选账户信息")
    public ResultVO<PageResponseVO<UserListRespVO>> queryUserAccountInfo(@RequestBody UserAccountReqVO reqVO){
       return ResultVO.success(userAccountService.selectUserInfo(reqVO));
    }
}
