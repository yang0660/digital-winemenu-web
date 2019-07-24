package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.IPackage;
import com.myicellar.digitalmenu.vo.response.ProductPriceRangeRespVO;

import java.util.List;

public interface IPackageMapperExt extends IPackageMapper{

    ProductPriceRangeRespVO selectPriceRange(Long supplierId);

    IPackage selectByWineId(Long wineId);

    List<IPackage> selectListByProductId(Long productId);

    Integer deleteByProductId(Long productId);

    Integer deleteByProductAndVolumeId(Long productId, Long volumeTypeId);
}