package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Role;
import com.myicellar.digitalmenu.dao.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface UserRoleMapperExt extends UserRoleMapper {

    int queryCountByRoleId(@Param("roleId") Long roleId);

    int deleteByUserId(Long userId);

    Set<String> selectRoleNamesByUserId(Long userId);

    Set<Role> selectRolesByUserId(Long userId);

    List<UserRole> selectByDepartmentIdAndRoleId(@Param("departmentId") Long departmentId, @Param("roleId") Long roleId);
    UserRole selectByUserIdDepartmentIdAndRoleId(@Param("userId") Long userId, @Param("departmentId") Long departmentId, @Param("roleId") Long roleId);
}