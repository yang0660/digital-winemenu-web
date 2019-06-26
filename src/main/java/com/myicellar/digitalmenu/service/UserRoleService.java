package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.UserRole;
import com.myicellar.digitalmenu.dao.mapper.UserRoleMapperExt;
import com.myicellar.digitalmenu.vo.request.UserRoleReqVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UserRoleService extends BaseService<Long, UserRole, UserRoleMapperExt> {

    public int queryCountByRoleId(Long roleId){
        return mapper.queryCountByRoleId(roleId);
    }

    @Transactional(readOnly = true)
    public List<UserRole> selectByRoleId(Long roleId) {
        Assert.notNull(roleId, "查询条件不能为空");
        UserRoleReqVO reqVO = new UserRoleReqVO();
        reqVO.setRoleId(roleId);
        return selectListMaxSize(reqVO);
    }

    @Transactional(readOnly = true)
    public List<UserRole> selectByDepartmentIdAndRoleId(Long departmentId, Long roleId) {
        return mapper.selectByDepartmentIdAndRoleId(departmentId, roleId);
    }

    @Transactional(readOnly = true)
    public UserRole selectByUserIdDepartmentIdAndRoleId(Long userId, Long departmentId, Long roleId) {
        return mapper.selectByUserIdDepartmentIdAndRoleId(userId, departmentId, roleId);
    }
}