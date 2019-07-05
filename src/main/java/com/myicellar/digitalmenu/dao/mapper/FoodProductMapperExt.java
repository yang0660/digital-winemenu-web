package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Food;
import com.myicellar.digitalmenu.dao.entity.FoodProduct;
import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.vo.request.FoodProductpageReqVO;

import java.util.List;

public interface FoodProductMapperExt extends FoodProductMapper{

    long selectCount(FoodProductpageReqVO reqVO);

    List<FoodProduct> selectList(FoodProductpageReqVO reqVO);

    long selectProductCount(FoodProductpageReqVO reqVO);

    List<Product> selectProductList(FoodProductpageReqVO reqVO);

    long selectFoodCount(FoodProductpageReqVO reqVO);

    List<Food> selectFoodList(FoodProductpageReqVO reqVO);
}