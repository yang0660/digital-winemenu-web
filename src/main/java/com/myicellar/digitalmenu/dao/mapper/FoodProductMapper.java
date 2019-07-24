package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.FoodProduct;
import org.apache.ibatis.annotations.Param;

public interface FoodProductMapper extends Mapper<FoodProduct> {
    int deleteByPrimaryKey(@Param("foodId") Long foodId, @Param("productId") Long productId);

    int insert(FoodProduct record);

    int insertSelective(FoodProduct record);

    FoodProduct selectByPrimaryKey(@Param("foodId") Long foodId, @Param("productId") Long productId);

    int updateByPrimaryKeySelective(FoodProduct record);

    int updateByPrimaryKey(FoodProduct record);
}