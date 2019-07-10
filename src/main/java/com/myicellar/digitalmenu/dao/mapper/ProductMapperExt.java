package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.vo.response.ProductInfoRespVO;
import com.myicellar.digitalmenu.vo.response.ProductPriceRangeRespVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapperExt extends ProductMapper{
    List<Product> selectList();

    ProductPriceRangeRespVO selectPriceRange(Long supplierId);

    List<ProductInfoRespVO> selectRecomendProductList(Long supplierId);

    List<ProductInfoRespVO> selectProductListByFoodId(Long foodId);

    List<ProductInfoRespVO> selectProductListByIds(@Param("productIds") List<Long> productIds);
}