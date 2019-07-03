package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.WineType;

public interface WineTypeMapper {
    int deleteByPrimaryKey(Long wineTypeId);

    int insert(WineType record);

    int insertSelective(WineType record);

    WineType selectByPrimaryKey(Long wineTypeId);

    int updateByPrimaryKeySelective(WineType record);

    int updateByPrimaryKey(WineType record);
}