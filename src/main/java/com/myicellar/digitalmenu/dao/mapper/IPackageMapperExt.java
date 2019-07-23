package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.IPackage;
import com.myicellar.digitalmenu.vo.request.PackageFilterReqVO;
import com.myicellar.digitalmenu.vo.request.WinePageReqVO;
import com.myicellar.digitalmenu.vo.response.PackageDetailRespVO;
import com.myicellar.digitalmenu.vo.response.PackageInfoRespVO;
import com.myicellar.digitalmenu.vo.response.PackageListRespVO;
import com.myicellar.digitalmenu.vo.response.PackagePriceRangeRespVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPackageMapperExt extends IPackageMapper{

    PackagePriceRangeRespVO selectPriceRange(Long supplierId);

    List<PackageInfoRespVO> selectRecomendPackageList(Long supplierId);

    List<PackageInfoRespVO> selectPackageListByFoodId(Long foodId);

    List<PackageInfoRespVO> selectPackageListByIds(@Param("packageIds") List<Long> packageIds);

    long selectResultCount(PackageFilterReqVO reqVO);

    List<PackageInfoRespVO> selectResult(PackageFilterReqVO reqVO);

    PackageDetailRespVO selectDetailById(Long packageId);

    IPackage selectByWineId(Long wineId);

    List<IPackage> selectListByProductId(Long productId);

    Integer deleteByProductId(Long productId);

    Integer deleteByProductAndVolumeId(Long productId, Long volumeTypeId);

    Long selectCount(WinePageReqVO reqVO);

    List<PackageListRespVO> selectList(WinePageReqVO reqVO);
}