package com.myicellar.digitalmenu.service;

import com.alibaba.fastjson.JSONObject;
import com.myicellar.digitalmenu.dao.entity.Department;
import com.myicellar.digitalmenu.dao.mapper.DepartmentMapperExt;
import com.myicellar.digitalmenu.dao.mapper.UserMapperExt;
import com.myicellar.digitalmenu.enums.DeptUpdateTypeEnum;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.vo.request.DepartmentSaveReqVO;
import com.myicellar.digitalmenu.vo.request.DepartmentUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.DepartmentRespVO;
import com.myicellar.digitalmenu.vo.response.DepartmentTreeRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DepartmentService extends BaseService<Long, Department, DepartmentMapperExt> {

    @Autowired
    private UserMapperExt userMapperExt;

    /**
     * 查询全量部门树状列表
     * @return
     */
    public List<DepartmentTreeRespVO> queryTree(){
        List<DepartmentTreeRespVO> resultList = new ArrayList<DepartmentTreeRespVO>();
        Long parentId = 0L;
        List<Department> topList = mapper.selectListByParentId(parentId);
        if(!CollectionUtils.isEmpty(topList)) {
            for (Department dept : topList) {
                DepartmentTreeRespVO respVO = ConvertUtils.convert(dept, DepartmentTreeRespVO.class);
                respVO.setName(respVO.getDepartmentName());
                List<DepartmentTreeRespVO> children = getChildrenList(dept.getDepartmentId());
                respVO.setChildren(children);
                resultList.add(respVO);
            }
        }

        return resultList;
    }

    /**
     * 下级部门树状列表查询
     * @param parentId
     * @return
     */
    public List<DepartmentTreeRespVO> getChildrenList(Long parentId){
        List<DepartmentTreeRespVO> resultList = new ArrayList<DepartmentTreeRespVO>();
        //子菜单列表，树结构
        List<Department> childrenList = mapper.selectListByParentId(parentId);
        if(!CollectionUtils.isEmpty(childrenList)) {
            for(Department dept : childrenList){
                DepartmentTreeRespVO respVO = ConvertUtils.convert(dept,DepartmentTreeRespVO.class);
                respVO.setName(respVO.getDepartmentName());
                //递归
                List<DepartmentTreeRespVO> children = getChildrenList(dept.getDepartmentId());
                respVO.setChildren(children);
                resultList.add(respVO);
            }
        }

        return resultList;
    }

    /**
     * 根据部门ID查询下级部门列表
     * @param parentId
     * @return
     */
    public List<Department> selectListByParentId(Long parentId){
        return mapper.selectListByParentId(parentId);
    }


    @Transactional(readOnly = true)
    public List<Department> selectAllChildrenList(Long departmentId) {
        List<Department> list = new ArrayList<>();
        Department department = selectByPrimaryKey(departmentId);
        if(department == null) {
            return list;
        }
        list.add(department);
        return selectAllChildrenList0(list, departmentId, 0, 20);
    }

    @Transactional(readOnly = true)
    public List<Department> selectAllChildrenList0(List<Department> allDepartmentList, Long parentDepartmentId, int curRecursionDepth, int maxRecursionDepth) {
        if(curRecursionDepth >= maxRecursionDepth || parentDepartmentId == null) {
            return allDepartmentList;
        }

        List<Department> childrenDepartment = mapper.selectListByParentId(parentDepartmentId);
        if(childrenDepartment == null && childrenDepartment.isEmpty()) {
            return allDepartmentList;
        }

        for(Department department: childrenDepartment) {
            allDepartmentList.add(department);
            selectAllChildrenList0(allDepartmentList, department.getDepartmentId(), ++ curRecursionDepth, maxRecursionDepth);
        }
        return allDepartmentList;
    }

    /**
     * 添加新部门
     * @param reqVO
     * @return
     */
    @Transactional
    public ResultVO addNew(DepartmentSaveReqVO reqVO){
        log.info("添加新部门，deptment:"+ JSONObject.toJSONString(reqVO));
        StringBuffer substr = new StringBuffer("添加新部门，deptment:")
                .append(JSONObject.toJSONString(reqVO));
        //参数校验
        ResultVO result = checkNewParam(reqVO);
        if(result!=null){
            substr.append(" ").append(result.getMessage());
            log.error(substr.toString());
            return result;
        }

        Department department = ConvertUtils.convert(reqVO,Department.class);
        Long departmentId = snowflakeIdWorker.nextId();
        department.setDepartmentId(departmentId);
        reqVO.setDepartmentId(departmentId);
        Date now = new Date();
        department.setCreateTime(now);
        department.setUpdateTime(now);
        int count = mapper.insertSelective(department);
        if(count==0){
            result = ResultVO.validError("添加失败!");
            return result;
        }

        result = ResultVO.success("添加成功!");
        return result;
    }

    /**
     * 更新部门信息
     * @param reqVO
     * @return
     */
    @Transactional
    public ResultVO updateDept(Byte type,DepartmentUpdateReqVO reqVO){
        log.info("更新部门信息，deptment:"+ JSONObject.toJSONString(reqVO));
        StringBuffer substr = new StringBuffer("更新部门信息，deptment:")
                .append(JSONObject.toJSONString(reqVO));
        //参数校验
        DeptUpdateTypeEnum typeEnum = DeptUpdateTypeEnum.enumOf(type);
        ResultVO result = null;
        if(typeEnum==null){
            result = ResultVO.validError("操作类型不匹配!");
            return result;
        }
        result = checkUpdateParam(typeEnum,reqVO);
        if(result!=null){
            substr.append(" ").append(result.getMessage());
            log.error(substr.toString());
            return result;
        }

        Department dept = new Department();
        dept.setDepartmentId(reqVO.getDepartmentId());
        if(DeptUpdateTypeEnum.DEPT_NAME.equals(typeEnum) || DeptUpdateTypeEnum.DEPT_CODE.equals(typeEnum)){
            if(DeptUpdateTypeEnum.DEPT_NAME.equals(typeEnum)) {
                dept.setDepartmentName(reqVO.getDepartmentName());
            }else {
                dept.setDepartmentCode(reqVO.getDepartmentCode());
            }

            Date now = new Date();
            dept.setUpdateTime(now);
            dept.setUpdateUser(reqVO.getUpdateUser());
            int count = mapper.updateByPrimaryKeySelective(dept);
            if(count==0){
                result = ResultVO.validError("更新失败!");
                return result;
            }
        }

        result = ResultVO.success("更新成功!");
        return result;
    }

    public DepartmentRespVO queryDeptInfo(Long departmentId){
        DepartmentRespVO respVO = null;
        Department department = mapper.selectByPrimaryKey(departmentId);
        respVO = ConvertUtils.convert(department,DepartmentRespVO.class);

        return respVO;
    }

    /**
     * 添加部门参数判断
     * @param reqVO
     * @return
     */
    public ResultVO checkNewParam(DepartmentSaveReqVO reqVO){
        ResultVO result = null;
        //部门名称判空
        result = checkDeptNameEmpty(reqVO.getDepartmentName());
        if(result!=null){
            return result;
        }
        //部门名称判重
        result = checkDeptNameRepeat(reqVO.getDepartmentName());
        if(result!=null){
            return result;
        }
        //部门编号判空
        result = checkDeptCodeEmpty(reqVO.getDepartmentCode());
        if(result!=null){
            return result;
        }
        //部门编号判重
        result = checkDeptCodeRepeat(reqVO.getDepartmentCode());
        if(result!=null){
            return result;
        }

        return result;
    }

    /**
     * 添加部门参数判断
     * @param reqVO
     * @return
     */
    public ResultVO checkUpdateParam(DeptUpdateTypeEnum typeEnum,DepartmentUpdateReqVO reqVO){
        ResultVO result = null;
        switch (typeEnum){
            case DEPT_NAME:
                //部门名称判空
                result = checkDeptNameEmpty(reqVO.getDepartmentName());
                if(result!=null){
                    return result;
                }
                //部门名称判重
                Department dept = mapper.selectByDeptName(reqVO.getDepartmentName());
                if(dept!=null && !dept.getDepartmentId().equals(reqVO.getDepartmentId())){
                    result = ResultVO.validError("部门名称已存在!");
                    return result;
                }
                break;
            case DEPT_CODE:
                //部门编号判空
                result = checkDeptCodeEmpty(reqVO.getDepartmentCode());
                if(result!=null){
                    return result;
                }
                //部门编号判重
                dept = mapper.selectByDeptCode(reqVO.getDepartmentCode());
                if(dept!=null && !dept.getDepartmentId().equals(reqVO.getDepartmentId())){
                    result = ResultVO.validError("部门编号已存在!");
                }
                break;
        }

        return result;
    }

    /**
     * 部门编号非空检测
     * @param deptCode
     * @return
     */
    public ResultVO checkDeptCodeEmpty(String deptCode){
        ResultVO result = null;
        if(StringUtils.isEmpty(deptCode)){
            result = ResultVO.validError("部门编号不能为空!");
            return result;
        }
        return result;
    }

    /**
     * 部门名称非空检测
     * @param deptName
     * @return
     */
    public ResultVO checkDeptNameEmpty(String deptName){
        ResultVO result = null;
        if(StringUtils.isEmpty(deptName)){
            result = ResultVO.validError("部门名称不能为空!");
            return result;
        }
        return result;
    }
    /**
     * 部门名称防重检测
     * @param deptName
     * @return
     */
    public ResultVO checkDeptNameRepeat(String deptName){
        ResultVO result = null;
        Department dept = mapper.selectByDeptName(deptName);
        if(dept!=null){
            result = ResultVO.validError("部门名称已存在!");
            return result;
        }
        return result;
    }

    /**
     * 上级部门ID非空检测
     * @param deptParentId
     * @return
     */
    public ResultVO checkDeptParentIdEmpty(Long deptParentId){
        ResultVO result = null;
        if(deptParentId==null || deptParentId==0L){
            result = ResultVO.validError("上级部门ID不能为空!");
            return result;
        }
        return result;
    }

    /**
     * 部门主管非空检测
     * @param deptMaster
     * @return
     */
    public ResultVO checkDeptMasterEmpty(Long deptMaster){
        ResultVO result = null;
        if(deptMaster==null || deptMaster==0L){
            ResultVO.validError("部门主管不能为空!");
        }
        return result;
    }

    /**
     * 部门编号防重检测
     * @param deptCode
     * @return
     */
    public ResultVO checkDeptCodeRepeat(String deptCode){
        ResultVO result = null;
        Department dept = mapper.selectByDeptCode(deptCode);
        if(dept!=null){
            result = ResultVO.validError("部门编号已存在!");
        }
        return result;
    }
}