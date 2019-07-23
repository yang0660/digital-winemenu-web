package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.service.FoodService;
import com.myicellar.digitalmenu.service.ProductService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.vo.request.FoodDetailReqVO;
import com.myicellar.digitalmenu.vo.request.FoodDisplayReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierIdReqVO;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/app/food")
@Api(tags = "用户页面", description = "/app/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private ProductService productService;

    /**
     * 推荐美食列表
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryRecomendFoodList")
    @AuthIgnore
    @ApiOperation("首页-推荐美食列表")
    public ResultVO<List<FoodRecommendRespVO>> queryRecomendFoodList(@RequestBody SupplierIdReqVO reqVO) {
        if(reqVO.getSupplierId()==null || reqVO.getSupplierId()==0L){
            return ResultVO.validError("supplierId cannot be empty！");
        }

        List<FoodRecommendRespVO> list = foodService.queryRecomendFoodList(reqVO);
        if(org.apache.commons.collections.CollectionUtils.isEmpty(list)){
            list = new ArrayList<FoodRecommendRespVO>();
        }

        return ResultVO.success(list);
    }


    /**
     * 查询美食列表（根据美食分类）
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListByFoodTypeId")
    @AuthIgnore
    @ApiOperation("查询美食列表（根据美食分类）")
    public ResultVO<List<FoodDisplayRespVO>> queryListByFoodTypeId(@RequestBody FoodDisplayReqVO reqVO) {
        if(reqVO.getFoodTypeId()==null || reqVO.getFoodTypeId()==0L){
            return ResultVO.validError("foodTypeId cannot be empty！");
        }

        List<FoodDisplayRespVO> list = foodService.queryListByFoodTypeId(reqVO.getFoodTypeId());
        if (CollectionUtils.isEmpty(list)) {
            list = new ArrayList<FoodDisplayRespVO>();
        }

        return ResultVO.success(list);
    }

    /**
     * 美食详情
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryDetailById")
    @AuthIgnore
    @ApiOperation("美食详情")
    public ResultVO<FoodDetailRespVO> queryDetailById(@RequestBody FoodDetailReqVO reqVO) {
        if(reqVO.getFoodId()==null || reqVO.getFoodId()==0L){
            return ResultVO.validError("foodId cannot be empty！");
        }

        FoodDetailRespVO respVO = foodService.queryDetailById(reqVO.getFoodId());
        if (respVO==null) {
            respVO = new FoodDetailRespVO();
        }else{
            List<ProductInfoRespVO> productList = productService.queryProductListByFoodId(reqVO.getFoodId());
            respVO.setProductList(productList);
        }

        return ResultVO.success(respVO);
    }


}
