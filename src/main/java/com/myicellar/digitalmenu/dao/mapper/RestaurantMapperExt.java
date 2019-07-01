package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Restaurant;
import com.myicellar.digitalmenu.vo.request.RestaurantListReqVO;
import com.myicellar.digitalmenu.vo.request.RestaurantReqVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RestaurantMapperExt  extends RestaurantMapper {
    List<Restaurant> queryListByParam(RestaurantListReqVO reqVO);

    Long queryCountByParam(RestaurantListReqVO reqVO);

    Restaurant selectByRestaurantNames(RestaurantReqVO reqVO);


}