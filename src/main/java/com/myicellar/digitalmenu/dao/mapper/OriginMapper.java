package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Origin;

public interface OriginMapper {
    int deleteByPrimaryKey(Long wineOriginId);

    int insert(Origin record);

    int insertSelective(Origin record);

    Origin selectByPrimaryKey(Long wineOriginId);

    int updateByPrimaryKeySelective(Origin record);

    int updateByPrimaryKey(Origin record);
}