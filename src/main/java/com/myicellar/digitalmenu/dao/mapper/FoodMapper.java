package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Food;

public interface FoodMapper extends Mapper<Food>{
    int deleteByPrimaryKey(Long foodId);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Long foodId);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKeyWithBLOBs(Food record);

    int updateByPrimaryKey(Food record);
}