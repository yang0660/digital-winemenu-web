package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Origin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OriginMapperExt extends OriginMapper{
    List<Origin> selectList();

    List<Origin> selectListBySupplierId(Long supplierId);

    List<Origin> selectListBySupplierIdAndCountryId(@Param("supplierId") Long supplierId,@Param("countryId") Long countryId);
}