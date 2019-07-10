package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.vo.response.ProductInfoRespVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapperExt extends ProductMapper{
    List<Product> selectList();

    List<ProductInfoRespVO> selectRecomendProductList(Long suppilerId);

    List<ProductInfoRespVO> selectProductListByFoodId(Long foodId);

    List<ProductInfoRespVO> selectProductListByIds(@Param("productIds") List<Long> productIds);
}