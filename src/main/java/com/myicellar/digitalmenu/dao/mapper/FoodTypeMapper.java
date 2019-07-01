package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.FoodType;

public interface FoodTypeMapper  extends Mapper<FoodType> {
    int deleteByPrimaryKey(Long foodTypeId);

    int insert(FoodType record);

    int insertSelective(FoodType record);

    FoodType selectByPrimaryKey(Long foodTypeId);

    int updateByPrimaryKeySelective(FoodType record);

    int updateByPrimaryKey(FoodType record);
}