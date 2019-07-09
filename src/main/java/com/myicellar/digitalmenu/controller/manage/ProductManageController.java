package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.service.ProductService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.ProductDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.ProductReqVO;
import com.myicellar.digitalmenu.vo.request.ProductUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ProductRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manage/product")
@Api(tags = "产品", description = "/manage/product")
public class ProductManageController {

    @Autowired
    private ProductService productService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 列表查询
     *
     * @param
     * @return
     */
    @PostMapping(value = "/queryList")
    @AuthIgnore
    @ApiOperation("列表查询")
    public ResultVO<List<ProductRespVO>> queryList() {
        List<Product> page = productService.queryList();

        PageResponseVO<ProductRespVO> resultPage = new PageResponseVO<ProductRespVO>();
        if(page!=null && page.isEmpty()){
            ResultVO.success(resultPage);
        }

        return ResultVO.validError("query is failed!");
    }




    /**
     * 新增
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/add")
    @AuthIgnore
    @ApiOperation("新增")
    public ResultVO<ProductRespVO> add(@RequestBody ProductReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Product product = ConvertUtils.convert(reqVO,Product.class);
        product.setProductId(snowflakeIdWorker.nextId());

        Date now = new Date();

        product.setUpdatedAt(now);
        int i = productService.insertSelective(product);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        ProductRespVO respVO = ConvertUtils.convert(product,ProductRespVO.class);
        ResultVO resultVO = ResultVO.success("save is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/update")
    @AuthIgnore
    @ApiOperation("修改")
    public ResultVO update(@RequestBody ProductUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Product product = ConvertUtils.convert(reqVO,Product.class);
        Date now = new Date();
        product.setUpdatedAt(now);
        int i = productService.updateByPrimaryKeySelective(product);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        ProductRespVO respVO = ConvertUtils.convert(product,ProductRespVO.class);
        ResultVO resultVO = ResultVO.success("update is success!");
        return resultVO.setData(respVO);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @AuthIgnore
    @ApiOperation("删除")
    public ResultVO update(@RequestBody ProductDeleteReqVO reqVO) {
        if(reqVO.getProductId()==null || reqVO.getProductId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = productService.deleteByPrimaryKey(reqVO.getProductId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(ProductReqVO reqVO){
        if(reqVO.getProductId()==null || reqVO.getProductId()==0L){
            throw new BizException("productId cannot be empty!");
        }

    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(ProductUpdateReqVO reqVO){
        if(reqVO.getProductId()==null || reqVO.getProductId()==0L){
            throw new BizException("productId cannot be empty!");
        }

    }

}
