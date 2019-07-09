package com.myicellar.digitalmenu.controller;

import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.service.ProductService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.ProductDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.ProductReqVO;
import com.myicellar.digitalmenu.vo.request.ProductUpdateReqVO;
import com.myicellar.digitalmenu.vo.request.SupplierIdReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ProductInfoRespVO;
import com.myicellar.digitalmenu.vo.response.ProductRespVO;
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

import java.util.ArrayList;
import java.util.Date;
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
    public ResultVO<List<ProductInfoRespVO>> queryRecomendProductList(SupplierIdReqVO reqVO) {
        List<ProductInfoRespVO> list = productService.queryRecomendProductList(reqVO.getSupplierId());
        if(CollectionUtils.isEmpty(list)){
            list = new ArrayList<ProductInfoRespVO>();
        }

        return ResultVO.success(list);
    }

}
