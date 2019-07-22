package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Product;
import org.apache.ibatis.annotations.Param;

public interface ProductMapperExt extends ProductMapper {

    Product selectByWineIdAndVintage(@Param("wineId") Long wineId,
                                     @Param("vintageTag") Long vintageTag);
}