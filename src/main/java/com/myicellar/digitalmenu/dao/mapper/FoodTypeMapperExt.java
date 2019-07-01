package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.vo.request.FoodTypeReqVO;
import com.myicellar.digitalmenu.vo.response.FoodTypeRespVO;

public interface FoodTypeMapperExt extends FoodTypeMapper{
     FoodType selectByFoodTypeNames(FoodTypeReqVO reqVO);
}