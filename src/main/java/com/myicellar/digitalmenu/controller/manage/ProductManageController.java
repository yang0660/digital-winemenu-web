package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.service.ProductManageService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.vo.request.ProductManageReqVO;
import com.myicellar.digitalmenu.vo.request.ProductPageReqVO;
import com.myicellar.digitalmenu.vo.request.ProductReqVO;
import com.myicellar.digitalmenu.vo.request.WineDetailReqVO;
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

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manage/product")
@Api(tags = "供应商管理-关联酒品", description = "/manage/product")
public class ProductManageController {
    @Autowired
    private ProductManageService productManageService;

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListPage")
    @ApiOperation("列表查询")
    public ResultVO<PageResponseVO<ProductListRespVO>> queryListPage(@RequestBody ProductPageReqVO reqVO) {
        PageResponseVO<ProductListRespVO> page = productManageService.queryPageList(reqVO);

        return ResultVO.success(page);
    }

    /**
     * 酒品年份下拉列表
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryVintageList")
    @ApiOperation("酒品年份下拉列表")
    public ResultVO<List<VintageRespVO>> queryVintageList(@RequestBody WineDetailReqVO reqVO) {
        List<VintageRespVO> list = productManageService.queryVintageList(reqVO.getWineId());

        return ResultVO.success(list);
    }

    /**
     * 详情查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryByProductId")
    @ApiOperation("详情查询")
    public ResultVO<ProductRespVO> queryByProductId(@RequestBody ProductReqVO reqVO) {
        ProductRespVO respVO = productManageService.queryByProductId(reqVO.getProductId());
        if (reqVO == null) {
            respVO = new ProductRespVO();
        }

        return ResultVO.success(respVO);
    }

    /**
     * 新增
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/add")
    @ApiOperation("新增")
    public ResultVO<Integer> add(@RequestBody ProductManageReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Integer result = productManageService.addNew(reqVO);

        return ResultVO.success(result);
    }

    /**
     * 修改
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/update")
    @ApiOperation("修改")
    @AuthIgnore
    public ResultVO update(@RequestBody ProductManageReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Integer result = productManageService.update(reqVO);

        return ResultVO.success(result);
    }

    /**
     * 删除
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/delete")
    @ApiOperation("删除")
    public ResultVO<Integer> update(@RequestBody ProductReqVO reqVO) {
        if (reqVO.getProductId() == null || reqVO.getProductId() == 0L) {
            return ResultVO.validError("parameter can not be empty!");
        }
        Integer result = productManageService.deleteByProductId(reqVO.getProductId());

        return ResultVO.success(result);
    }

    /**
     * 校验新增参数
     *
     * @param reqVO
     */
    private void checkNewParam(ProductManageReqVO reqVO) {
        if (reqVO.getWineId() == null || reqVO.getWineId() == 0L) {
            throw new BizException("wineId can not be empty!");
        }
        if (reqVO.getVintageTag() == null || reqVO.getVintageTag() == 0L) {
            throw new BizException("vintageTag can not be empty!");
        }
        if (reqVO.getSupplierId() == null || reqVO.getSupplierId() == 0L) {
            throw new BizException("supplierId can not be empty!");
        }
        if (CollectionUtils.isEmpty(reqVO.getVolumePrices())) {
            throw new BizException("volume and price can not be empty!");
        }
        Product product = productManageService.selectBySupplierWineIdAndVintage(reqVO.getSupplierId(), reqVO.getWineId(), reqVO.getVintageTag());
        if (product != null) {
            throw new BizException("supplier contains this wineVintage already!");
        }

    }

    /**
     * 校验修改参数
     *
     * @param reqVO
     */
    private void checkUpdateParam(ProductManageReqVO reqVO) {
        if (reqVO.getProductId() == null || reqVO.getProductId() == 0L) {
            throw new BizException("productId can not be empty!");
        }
        if (reqVO.getWineId() == null || reqVO.getWineId() == 0L) {
            throw new BizException("wineId can not be empty!");
        }
        if (reqVO.getVintageTag() == null || reqVO.getVintageTag() == 0L) {
            throw new BizException("vintageTag can not be empty!");
        }
        if (reqVO.getSupplierId() == null || reqVO.getSupplierId() == 0L) {
            throw new BizException("supplierId can not be empty!");
        }
        if (CollectionUtils.isEmpty(reqVO.getVolumePrices())) {
            throw new BizException("volume and price can not be empty!");
        }
        Product product = productManageService.selectBySupplierWineIdAndVintage(reqVO.getSupplierId(), reqVO.getWineId(), reqVO.getVintageTag());
        if (product != null && !reqVO.getProductId().equals(product.getProductId())) {
            throw new BizException("supplier contains this wineVintage already!");
        }
    }

}
