package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.vo.response.GrantPermissionRespVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface RolePermissionMapperExt extends RolePermissionMapper{

    Set<Long> selectPermListByRoleId(@Param("roleId") Long roleId);

    Integer deleteByRolePermId(@Param("roleId") Long roleId,@Param("permissionId") Long permId);

    List<GrantPermissionRespVO> selectPermListByMenuRoleId(@Param("menuId") Long menuId,
                                                           @Param("roleId") Long roleId);
}