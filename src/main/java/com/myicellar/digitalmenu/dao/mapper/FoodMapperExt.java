package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Food;
import com.myicellar.digitalmenu.vo.request.FoodPageReqVO;

import java.util.List;

public interface FoodMapperExt extends FoodMapper{

    long selectCount(FoodPageReqVO reqVO);

    List<Food> selectList(FoodPageReqVO reqVO);

}