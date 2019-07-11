package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.service.PackageService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.vo.request.PackageDetailReqVO;
import com.myicellar.digitalmenu.vo.request.PackageFilterReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierIdReqVO;
import com.myicellar.digitalmenu.vo.request.WishListReqVO;
import com.myicellar.digitalmenu.vo.response.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/app/package")
@Api(tags = "用户页面-酒品", description = "/app/package")
public class PackageController {

    @Autowired
    private PackageService packageService;

    /**
     * 推荐酒品列表
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryRecomendPackageList")
    @AuthIgnore
    @ApiOperation("推荐酒品列表")
    public ResultVO<List<PackageInfoRespVO>> queryRecomendPackageList(@RequestBody SupplierIdReqVO reqVO) {
        List<PackageInfoRespVO> list = packageService.queryRecomendPackageList(reqVO.getSupplierId());
        if(CollectionUtils.isEmpty(list)){
            list = new ArrayList<PackageInfoRespVO>();
        }

        return ResultVO.success(list);
    }

    /**
     * WISHLIST酒品列表
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryPackageListByIds")
    @AuthIgnore
    @ApiOperation("WISHLIST酒品列表")
    public ResultVO<List<PackageInfoRespVO>> queryPackageListByIds(@RequestBody WishListReqVO reqVO) {
        List<PackageInfoRespVO> list = new ArrayList<PackageInfoRespVO>();
        if(!CollectionUtils.isEmpty(reqVO.getPackageIds())) {
            list = packageService.queryPackageListByIds(reqVO.getPackageIds());
        }

        return ResultVO.success(list);
    }

    /**
     * 查询酒品价格区间（根据供应商ID）
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryPriceRange")
    @AuthIgnore
    @ApiOperation("查询酒品价格区间（根据供应商ID）")
    public ResultVO<PackagePriceRangeRespVO> queryPriceRange(@RequestBody SupplierIdReqVO reqVO) {
        PackagePriceRangeRespVO respVO = packageService.queryPriceRange(reqVO.getSupplierId());
        if(respVO==null || respVO.getMaxPackagePrice().equals(new BigDecimal(0.00))){
            respVO = new PackagePriceRangeRespVO();
        }else if(respVO.getMaxPackagePrice().equals(respVO.getMinPackagePrice())){
            respVO.setMinPackagePrice(new BigDecimal(0.00));
        }

        return ResultVO.success(respVO);
    }

    /**
     * package详情
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryDetailById")
    @AuthIgnore
    @ApiOperation("package详情")
    public ResultVO<PackageDetailRespVO> queryDetailById(@RequestBody PackageDetailReqVO reqVO) {
        PackageDetailRespVO respVO=packageService.queryDetailById(reqVO.getPackageId());
        return ResultVO.success(null);
    }

    /**
     * 筛选结果总量统计
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryResultCount")
    @AuthIgnore
    @ApiOperation("筛选结果总量统计")
    public ResultVO<Long> queryResultCount(@RequestBody PackageFilterReqVO reqVO) {
        Long count = packageService.queryResultCount(reqVO);

        return ResultVO.success(count);
    }

    /**
     * 筛选结果页列表
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryResultPage")
    @AuthIgnore
    @ApiOperation("筛选结果页列表")
    public ResultVO<PageResponseVO<PackageInfoRespVO>> queryResultPage(@RequestBody PackageFilterReqVO reqVO) {
        PageResponseVO<PackageInfoRespVO> page = packageService.queryResultPage(reqVO);

        return ResultVO.success(page);
    }


}
