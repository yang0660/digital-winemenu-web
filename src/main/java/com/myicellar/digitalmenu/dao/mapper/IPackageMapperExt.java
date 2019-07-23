package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.IPackage;
import com.myicellar.digitalmenu.vo.response.PackagePriceRangeRespVO;

import java.util.List;

public interface IPackageMapperExt extends IPackageMapper{

    PackagePriceRangeRespVO selectPriceRange(Long supplierId);

    IPackage selectByWineId(Long wineId);

    List<IPackage> selectListByProductId(Long productId);

    Integer deleteByProductId(Long productId);

    Integer deleteByProductAndVolumeId(Long productId, Long volumeTypeId);
}