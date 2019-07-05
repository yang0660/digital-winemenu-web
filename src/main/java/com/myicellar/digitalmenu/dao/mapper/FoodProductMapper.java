package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.FoodProduct;

public interface FoodProductMapper extends Mapper<FoodProduct>{
    int deleteByPrimaryKey(Long foodId);

    int insert(FoodProduct record);

    int insertSelective(FoodProduct record);

    FoodProduct selectByPrimaryKey(Long foodId);

    int updateByPrimaryKeySelective(FoodProduct record);

    int updateByPrimaryKey(FoodProduct record);
}