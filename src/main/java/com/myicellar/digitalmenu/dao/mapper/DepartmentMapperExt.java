package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapperExt extends DepartmentMapper {

    List<Department> selectListByParentId(Long parentId);

    Department selectByDeptCode(@Param("departmentCode") String deptCode);

    Department selectByDeptName(@Param("departmentName") String deptName);
}