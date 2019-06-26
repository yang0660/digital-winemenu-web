package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.UserAccount;
import com.myicellar.digitalmenu.vo.request.UserAccountReqVO;
import com.myicellar.digitalmenu.vo.response.UserListRespVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserAccountMapperExt extends UserAccountMapper {

    UserAccount selectByUserNameAndType(@Param("userName") String userName,
                                        @Param("userType") Byte userType);

    UserAccount selectByUserName(@Param("userName") String userName);

    UserAccount selectByMobile(String mobile);

    UserAccount selectByWxOpenId(String wxOpenId);

    List<UserAccount> selectListAll();

    Set<String> selectPermissionsByUserId(Long userId);


    List<UserAccount> selectByRoleId(@Param("roleId") Long roleId);
    List<UserAccount> selectByRoleIdAndDepartmentId(@Param("roleId") Long roleId, @Param("departmentId") Long departmentId);

    UserListRespVO selectUserInfoById(Long userId);

    List<UserAccount> selectByRealName(@Param("realName") String realName);

    List<UserListRespVO> selectUserInfo(UserAccountReqVO reqVO);
    Integer countUserInfo(UserAccountReqVO reqVO);

    @MapKey("userId")
    Map<Long,UserAccount> selectUserMapByIds(@Param("userIds") Set<Long> userIds);
}
