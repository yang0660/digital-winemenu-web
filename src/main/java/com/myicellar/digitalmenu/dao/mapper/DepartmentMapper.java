package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Department;

public interface DepartmentMapper extends Mapper<Department>{
    int deleteByPrimaryKey(Long departmentId);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Long departmentId);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}