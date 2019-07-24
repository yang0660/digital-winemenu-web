package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Food;
import com.myicellar.digitalmenu.vo.request.FoodPageReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierIdReqVO;
import com.myicellar.digitalmenu.vo.response.FoodDetailBGRespVO;
import com.myicellar.digitalmenu.vo.response.FoodDetailRespVO;
import com.myicellar.digitalmenu.vo.response.FoodDisplayRespVO;
import com.myicellar.digitalmenu.vo.response.FoodRecommendRespVO;

import java.util.List;

public interface FoodMapperExt extends FoodMapper {

    long selectCount(FoodPageReqVO reqVO);

    List<Food> selectList(FoodPageReqVO reqVO);

    List<FoodDisplayRespVO> selectListByFoodTypeId(Long foodTypeId);

    FoodDetailRespVO selectDetailById(Long foodId);

    List<FoodRecommendRespVO> selectRecomendFoodList(SupplierIdReqVO reqVO);

    List<FoodRecommendRespVO> selectFoodListByProductId(Long productId);

    FoodDetailBGRespVO selectFoodDetail(Long foodId);

    Food selectByFoodName(String foodNameEng);
}