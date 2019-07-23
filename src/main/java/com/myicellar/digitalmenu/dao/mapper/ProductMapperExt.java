package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.vo.request.PackageFilterReqVO;
import com.myicellar.digitalmenu.vo.request.WinePageReqVO;
import com.myicellar.digitalmenu.vo.response.ProductDetailRespVO;
import com.myicellar.digitalmenu.vo.response.ProductInfoRespVO;
import com.myicellar.digitalmenu.vo.response.ProductListRespVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapperExt extends ProductMapper {

    Product selectByWineIdAndVintage(@Param("wineId") Long wineId,
                                     @Param("vintageTag") Long vintageTag);
    
    List<ProductInfoRespVO> selectRecomendProductList(Long supplierId);

    List<ProductInfoRespVO> selectProductListByFoodId(Long foodId);

    List<ProductInfoRespVO> selectProductListByIds(@Param("ProductIds") List<Long> ProductIds);

    long selectResultCount(PackageFilterReqVO reqVO);

    List<ProductInfoRespVO> selectResult(PackageFilterReqVO reqVO);

    ProductDetailRespVO selectDetailById(Long ProductId);

    Long selectCount(WinePageReqVO reqVO);

    List<ProductListRespVO> selectList(WinePageReqVO reqVO);

    List<Product> selectListBySupplierId(Long supplierId);
}