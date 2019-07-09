package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Food;
import com.myicellar.digitalmenu.vo.request.FoodDetailReqVO;
import com.myicellar.digitalmenu.vo.request.FoodDisplayReqVO;
import com.myicellar.digitalmenu.vo.request.FoodPageReqVO;
import com.myicellar.digitalmenu.vo.response.FoodDetailRespVO;
import com.myicellar.digitalmenu.vo.response.FoodDisplayRespVO;

import java.util.List;

public interface FoodMapperExt extends FoodMapper{

    long selectCount(FoodPageReqVO reqVO);

    List<Food> selectList(FoodPageReqVO reqVO);

    List<FoodDisplayRespVO> queryListByFoodTypeId(FoodDisplayReqVO reqVO);

    FoodDetailRespVO queryListByFoodId(FoodDetailReqVO reqVO);
}