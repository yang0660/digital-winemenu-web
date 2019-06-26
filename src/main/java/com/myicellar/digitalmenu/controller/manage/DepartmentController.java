package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.Department;
import com.myicellar.digitalmenu.service.DepartmentService;
import com.myicellar.digitalmenu.service.UserService;
import com.myicellar.digitalmenu.shiro.SysLog;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.PrincipalContext;
import com.myicellar.digitalmenu.vo.request.DepartmentDqVO;
import com.myicellar.digitalmenu.vo.request.DepartmentReqVO;
import com.myicellar.digitalmenu.vo.request.DepartmentSaveReqVO;
import com.myicellar.digitalmenu.vo.request.DepartmentUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.DepartmentRespVO;
import com.myicellar.digitalmenu.vo.response.DepartmentTreeRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manage/department")
@Api(tags = "部门管理", description = "/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    /**
     * 部门树状列表查询
     * @return
     */
    @PostMapping("queryTree")
    @ApiOperation(value = "部门树状列表查询")
    public ResultVO<List<DepartmentTreeRespVO>> queryList() {
        List<DepartmentTreeRespVO> departmentTree = departmentService.queryTree();
        return ResultVO.success(departmentTree);
    }

    @PostMapping("queryList")
    @ApiOperation(value = "查询所有部门")
    public ResultVO<List<DepartmentRespVO>> queryList(@RequestBody DepartmentReqVO reqVO) {
        List<Department> departmentList = departmentService.selectListMaxSize(reqVO);
        return ResultVO.success(ConvertUtils.convert(departmentList, DepartmentRespVO.class));
    }

    /**
     * 根据ID查询部门详情
     * @param reqVO
     * @return
     */
    @PostMapping("queryById")
    @ApiOperation("根据ID查询部门详情")
    public ResultVO<DepartmentRespVO> queryById(@RequestBody DepartmentDqVO reqVO) {
        DepartmentRespVO respVO = departmentService.queryDeptInfo(reqVO.getDepartmentId());
        return ResultVO.success(respVO);
    }

    /**
     * 保存新部门
     * @param reqVO
     * @return
     */
    @PostMapping("addNew")
    @ApiOperation(value = "添加部门")
    @SysLog(operation = "添加部门")
    @RequiresPermissions(value = {"dept:add"})
    public ResultVO addNew(@RequestBody DepartmentSaveReqVO reqVO) {
        Long currentUserId = PrincipalContext.getCurrentUserId();
        reqVO.setCreateUser(currentUserId);
        reqVO.setUpdateUser(currentUserId);
        ResultVO result = departmentService.addNew(reqVO);
        return result;
    }

    /**
     * 更新部门信息
     * @param reqVO
     * @return
     */
    @PostMapping("updateDept")
    @ApiOperation(value = "修改部门")
    @SysLog(operation = "修改部门")
    @RequiresPermissions(value = {"dept:edit"})
    public ResultVO saveOrUpdate(@RequestBody DepartmentUpdateReqVO reqVO) {
        Byte type = reqVO.getType();
        Long currentUserId = PrincipalContext.getCurrentUserId();
        reqVO.setUpdateUser(currentUserId);
        ResultVO result = departmentService.updateDept(type,reqVO);

        return result;
    }

    /**
     * 删除
     * @param reqVO
     * @return
     */
    @PostMapping("deleteById")
    @ApiOperation("根据ID删除部门")
    @SysLog(operation = "删除部门")
    @RequiresPermissions(value = {"dept:delete"})
    public ResultVO deleteById(@RequestBody DepartmentDqVO reqVO) {
        if(reqVO.getDepartmentId()==null) {
            return ResultVO.validError("部门ID不能为空!");
        }
        if(reqVO.getDepartmentId()==1L) { //内置初始化部门ID
            return ResultVO.validError("该部门不能删除!");
        }
        List<Department> firstChildren = departmentService.selectListByParentId(reqVO.getDepartmentId());
        if(!CollectionUtils.isEmpty(firstChildren)){
            return ResultVO.validError("该部门存在下级部门，不能删除!");
        }
        int userCount = userService.queryCountByDeptId(reqVO.getDepartmentId());
        if(userCount>0){
            return ResultVO.validError("存在该部门的用户，不能删除!");
        }

        int count = departmentService.deleteByPrimaryKey(reqVO.getDepartmentId());
        if(count==0){
            return ResultVO.validError("删除失败!");
        }
        return ResultVO.success("删除成功!");
    }
}
