package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.User;
import com.myicellar.digitalmenu.vo.request.UserPageReqVO;
import com.myicellar.digitalmenu.vo.response.UserListRespVO;
import com.myicellar.digitalmenu.vo.response.UserRespVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapperExt extends UserMapper {

    int queryCountByDeptId(@Param("departmentId") Long deptId);

    Long selectCountByCondition(UserPageReqVO req);

    List<UserListRespVO> selectListByCondition(UserPageReqVO req);

    User selectByUserCode(String userCode);

    List<UserRespVO> selectByRoleId(@Param("roleId") Long roleId);
}