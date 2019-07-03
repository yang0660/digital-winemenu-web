package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Critics;

public interface CriticsMapper {
    int deleteByPrimaryKey(Long wineCriticsId);

    int insert(Critics record);

    int insertSelective(Critics record);

    Critics selectByPrimaryKey(Long wineCriticsId);

    int updateByPrimaryKeySelective(Critics record);

    int updateByPrimaryKey(Critics record);
}