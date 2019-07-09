package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Supplier;
import com.myicellar.digitalmenu.vo.request.SupplierIndexReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierPageReqVO;
import com.myicellar.digitalmenu.vo.response.FoodRecommendRespVO;

import java.util.List;

public interface SupplierMapperExt extends SupplierMapper {

    long selectCount(SupplierPageReqVO reqVO);

    List<Supplier> selectList(SupplierPageReqVO reqVO);

    List<FoodRecommendRespVO> queryListByRecommend(SupplierIndexReqVO reqVO);

}