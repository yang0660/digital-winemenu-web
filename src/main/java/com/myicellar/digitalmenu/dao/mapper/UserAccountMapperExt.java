package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.UserAccount;
import org.apache.ibatis.annotations.Param;

public interface UserAccountMapperExt extends UserAccountMapper {

    UserAccount selectByUserNameAndType(@Param("userName") String userName,
                                        @Param("userType") Byte userType);

    UserAccount selectByUserName(@Param("userName") String userName);
}
