package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.Product;
import com.myicellar.digitalmenu.service.PackageService;
import com.myicellar.digitalmenu.service.ProductService;
import com.myicellar.digitalmenu.service.WineService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.vo.request.PackageReqVO;
import com.myicellar.digitalmenu.vo.request.ProductReqVO;
import com.myicellar.digitalmenu.vo.request.WinePageReqVO;
import com.myicellar.digitalmenu.vo.response.PackageRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.WineRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/manage/pkg")
@Api(tags = "酒品管理", description = "/manage/pkg")
public class PackageManageController {

    @Autowired
    private WineService wineService;
    @Autowired
    private PackageService packageService;
    @Autowired
    private ProductService productService;

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListPage")
    @AuthIgnore
    @ApiOperation("列表查询")
    public ResultVO<PageResponseVO<WineRespVO>> queryListPage(@RequestBody WinePageReqVO reqVO) {
        PageResponseVO<WineRespVO> page = wineService.queryPageList(reqVO);

        return ResultVO.success(page);
    }

    /**
     * 详情查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryByProductId")
    @AuthIgnore
    @ApiOperation("详情查询")
    public ResultVO<PackageRespVO> queryByProductId(@RequestBody ProductReqVO reqVO) {
        PackageRespVO respVO = packageService.queryByProductId(reqVO.getProductId());
        if(reqVO==null){
            respVO = new PackageRespVO();
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
    @AuthIgnore
    @ApiOperation("新增")
    public ResultVO<Integer> add(@RequestBody PackageReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Integer result = packageService.addNew(reqVO);

        return ResultVO.success(result);
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
    public ResultVO update(@RequestBody PackageReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Integer result = packageService.update(reqVO);

        return ResultVO.success(result);
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
    public ResultVO<Integer> update(@RequestBody ProductReqVO reqVO) {
        if(reqVO.getProductId()==null || reqVO.getProductId()==0L){
            return ResultVO.validError("parameter can not be empty!");
        }
        Integer result = packageService.deleteByProductId(reqVO.getProductId());

        return ResultVO.success(result);
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(PackageReqVO reqVO){
        if(reqVO.getWineId()==null || reqVO.getWineId()==0L){
            throw new BizException("wineId can not be empty!");
        }
        if(reqVO.getVintageTag()==null || reqVO.getVintageTag()==0L){
            throw new BizException("vintageTag can not be empty!");
        }
        if(reqVO.getSupplierId()==null || reqVO.getSupplierId()==0L){
            throw new BizException("supplierId can not be empty!");
        }
        if(CollectionUtils.isEmpty(reqVO.getVolumePrices())){
            throw new BizException("volume and price can not be empty!");
        }

    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(PackageReqVO reqVO){
        if(reqVO.getProductId()==null || reqVO.getProductId()==0L){
            throw new BizException("productId can not be empty!");
        }
        if(reqVO.getWineId()==null || reqVO.getWineId()==0L){
            throw new BizException("wineId can not be empty!");
        }
        if(reqVO.getVintageTag()==null || reqVO.getVintageTag()==0L){
            throw new BizException("vintageTag can not be empty!");
        }
        if(reqVO.getSupplierId()==null || reqVO.getSupplierId()==0L){
            throw new BizException("supplierId can not be empty!");
        }
        if(CollectionUtils.isEmpty(reqVO.getVolumePrices())){
            throw new BizException("volume and price can not be empty!");
        }
        Product product = productService.selectByWineIdAndVintage(reqVO.getWineId(),reqVO.getVintageTag());
        if(product!=null){
            throw new BizException("wine contains this vintage already!");
        }
    }

}
