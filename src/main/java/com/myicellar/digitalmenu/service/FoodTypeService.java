package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.dao.entity.Restaurant;
import com.myicellar.digitalmenu.dao.mapper.FoodTypeMapperExt;
import com.myicellar.digitalmenu.dao.mapper.RestaurantMapperExt;
import com.myicellar.digitalmenu.vo.request.FoodTypeReqVO;
import com.myicellar.digitalmenu.vo.request.RestaurantReqVO;
import com.myicellar.digitalmenu.vo.response.FoodTypeRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class FoodTypeService extends BaseService<Long, FoodType, FoodTypeMapperExt> {

    @Transactional
    public FoodType selectByPrimaryKey(Long foodtypeId){
        return mapper.selectByPrimaryKey(foodtypeId);
    }

    public  FoodType selectByFoodTypeNames(FoodTypeReqVO reqVO){
        return mapper.selectByFoodTypeNames(reqVO);
    }
}