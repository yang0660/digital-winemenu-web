package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Role;
import org.apache.ibatis.annotations.Param;

public interface RoleMapperExt extends RoleMapper{

    Role selectByRoleName(@Param("roleName") String roleName);
}