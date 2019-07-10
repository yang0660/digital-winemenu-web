package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.service.ProductService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.vo.request.SupplierIdReqVO;
import com.myicellar.digitalmenu.vo.request.WishListReqVO;
import com.myicellar.digitalmenu.vo.response.ProductInfoRespVO;
import com.myicellar.digitalmenu.vo.response.ProductPriceRangeRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
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
@Api(tags = "用户页面-酒品", description = "/app/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 推荐酒品列表
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryRecomendProductList")
    @AuthIgnore
    @ApiOperation("推荐酒品列表")
    public ResultVO<List<ProductInfoRespVO>> queryRecomendProductList(@RequestBody SupplierIdReqVO reqVO) {
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
    public ResultVO<List<ProductInfoRespVO>> queryProductListByIds(@RequestBody WishListReqVO reqVO) {
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
        ProductPriceRangeRespVO respVO = productService.queryPriceRange(reqVO.getSupplierId());
        if(respVO==null || respVO.getMaxProductPrice().equals(new BigDecimal(0.00))){
            respVO = new ProductPriceRangeRespVO();
        }else if(respVO.getMaxProductPrice().equals(respVO.getMinProductPrice())){
            respVO.setMinProductPrice(new BigDecimal(0.00));
        }

        return ResultVO.success(respVO);
    }

}
