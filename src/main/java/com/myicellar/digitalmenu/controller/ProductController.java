package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.service.FoodService;
import com.myicellar.digitalmenu.service.PackageService;
import com.myicellar.digitalmenu.service.ProductService;
import com.myicellar.digitalmenu.service.WineVintageScoreService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.vo.request.PackageFilterReqVO;
import com.myicellar.digitalmenu.vo.request.ProductDetailReqVO;
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
@RequestMapping("/app/product")
@Api(tags = "用户页面", description = "/app/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private WineVintageScoreService wineVintageScoreService;
    @Autowired
    private PackageService packageService;

    /**
     * 推荐酒品列表
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryRecomendProductList")
    @AuthIgnore
    @ApiOperation("DRINK页-推荐酒品列表")
    public ResultVO<List<ProductInfoRespVO>> queryRecomendPackageList(@RequestBody SupplierIdReqVO reqVO) {
        List<ProductInfoRespVO> list = productService.queryRecomendProductList(reqVO.getSupplierId());
        if(CollectionUtils.isEmpty(list)){
            list = new ArrayList<ProductInfoRespVO>();
        }

        return ResultVO.success(list);
    }

    /**
     * WISHLIST酒品列表
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryProductListByIds")
    @AuthIgnore
    @ApiOperation("WISHLIST酒品列表")
    public ResultVO<List<ProductInfoRespVO>> queryPackageListByIds(@RequestBody WishListReqVO reqVO) {
        List<ProductInfoRespVO> list = new ArrayList<ProductInfoRespVO>();
        if(!CollectionUtils.isEmpty(reqVO.getProductIds())) {
            list = productService.queryProductListByIds(reqVO.getProductIds());
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
    public ResultVO<ProductPriceRangeRespVO> queryPriceRange(@RequestBody SupplierIdReqVO reqVO) {
        ProductPriceRangeRespVO respVO = packageService.queryPriceRange(reqVO.getSupplierId());
        if(respVO==null || respVO.getMaxProductPrice().equals(new BigDecimal(0.00))){
            respVO = new ProductPriceRangeRespVO();
        }else if(respVO.getMaxProductPrice().equals(respVO.getMinProductPrice())){
            respVO.setMinProductPrice(new BigDecimal(0.00));
        }

        return ResultVO.success(respVO);
    }

    /**
     * 酒品详情
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryDetailById")
    @AuthIgnore
    @ApiOperation("酒品详情")
    public ResultVO<ProductDetailRespVO> queryDetailById(@RequestBody ProductDetailReqVO reqVO) {
        ProductDetailRespVO respVO = productService.queryDetailById(reqVO.getProductId());
        List<ScoreRespVO> scoreList = wineVintageScoreService.queryScoreListByProductId(reqVO.getProductId());
        if(!CollectionUtils.isEmpty(scoreList)) {
            respVO.setScoreList(scoreList);
        }

        return ResultVO.success(respVO);
    }

    /**
     * 酒品详情-美食推荐列表
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryFoodListByProductId")
    @AuthIgnore
    @ApiOperation("酒品详情-美食推荐列表")
    public ResultVO<List<FoodRecommendRespVO>> queryFoodListByProductId(@RequestBody ProductDetailReqVO reqVO) {
        List<FoodRecommendRespVO> list = foodService.queryFoodListByProductId(reqVO.getProductId());
        return ResultVO.success(list);
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
        Long count = productService.queryResultCount(reqVO);

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
    public ResultVO<PageResponseVO<ProductInfoRespVO>> queryResultPage(@RequestBody PackageFilterReqVO reqVO) {
        PageResponseVO<ProductInfoRespVO> page = productService.queryResultPage(reqVO);

        return ResultVO.success(page);
    }


}
