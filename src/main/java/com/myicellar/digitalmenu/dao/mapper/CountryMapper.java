package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Country;

public interface CountryMapper extends Mapper<Country>{
    int deleteByPrimaryKey(Long countryId);

    int insert(Country record);

    int insertSelective(Country record);

    Country selectByPrimaryKey(Long countryId);

    int updateByPrimaryKeySelective(Country record);

    int updateByPrimaryKey(Country record);
}