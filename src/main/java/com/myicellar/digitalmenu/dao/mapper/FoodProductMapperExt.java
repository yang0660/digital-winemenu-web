package com.myicellar.digitalmenu.dao.mapper;

import java.util.List;

public interface FoodProductMapperExt extends FoodProductMapper {

    List<Long> selectListByFoodId(Long foodId);

}