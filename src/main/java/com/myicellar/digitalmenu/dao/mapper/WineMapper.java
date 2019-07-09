package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Wine;

public interface WineMapper extends Mapper<Wine>{
    int deleteByPrimaryKey(Long wineId);

    int insert(Wine record);

    int insertSelective(Wine record);

    Wine selectByPrimaryKey(Long wineId);

    int updateByPrimaryKeySelective(Wine record);

    int updateByPrimaryKey(Wine record);
}