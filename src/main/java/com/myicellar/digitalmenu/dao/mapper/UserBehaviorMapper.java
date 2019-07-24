package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.UserBehavior;

public interface UserBehaviorMapper extends Mapper<UserBehavior> {
    int insert(UserBehavior record);

    int insertSelective(UserBehavior record);
}