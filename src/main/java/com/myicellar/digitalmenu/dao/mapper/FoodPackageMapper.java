package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.FoodPackage;
import org.apache.ibatis.annotations.Param;

public interface FoodPackageMapper extends Mapper<FoodPackage>{
    int deleteByPrimaryKey(@Param("foodId") Long foodId, @Param("packageId") Long packageId);

    int insert(FoodPackage record);

    int insertSelective(FoodPackage record);

    FoodPackage selectByPrimaryKey(@Param("foodId") Long foodId, @Param("packageId") Long packageId);

    int updateByPrimaryKeySelective(FoodPackage record);

    int updateByPrimaryKey(FoodPackage record);
}