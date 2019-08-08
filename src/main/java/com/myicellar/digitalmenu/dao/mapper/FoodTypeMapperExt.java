package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.vo.request.FoodTypePageReqVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodTypeMapperExt extends FoodTypeMapper {

    long selectCount(FoodTypePageReqVO reqVO);

    List<FoodType> selectList(FoodTypePageReqVO reqVO);

    List<FoodType> selectListBySupplierId(Long supplierId);

    FoodType selectByFoodTypeName(@Param("FoodTypeName") String FoodTypeName,@Param("supplierId") Long supplierId);
}