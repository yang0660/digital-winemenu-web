package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.FoodProduct;
import com.myicellar.digitalmenu.service.FoodProductService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.FoodProductDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.FoodProductReqVO;
import com.myicellar.digitalmenu.vo.request.FoodProductUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.FoodProductRespVO;
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

@RestController
@Slf4j
@RequestMapping("/manage/foodProduct")
@Api(tags = "美食-产品关联信息", description = "/manage/foodProduct")
public class FoodProductManageController {

    @Autowired
    private FoodProductService foodProductService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;


    /**
     * 美食-产品关联信息查询
     *
     * @param reqVO
     * @return
     */


    /**
     * 根据foodId查询product列表
     *
     * @param reqVO
     * @return
     */


    /**
     * 根据productId查询food列表
     *
     * @param reqVO
     * @return
     */

    /**
     * 新增
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/add")
    @AuthIgnore
    @ApiOperation("新增")
    public ResultVO<FoodProductRespVO> add(@RequestBody FoodProductReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        FoodProduct foodProduct = ConvertUtils.convert(reqVO,FoodProduct.class);
        foodProduct.setFoodId(snowflakeIdWorker.nextId());
        foodProduct.setCreatedBy(0L);
        foodProduct.setUpdatedBy(0L);
        Date now = new Date();
        foodProduct.setCreatedAt(now);
        foodProduct.setUpdatedAt(now);
        int i = foodProductService.insertSelective(foodProduct);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        FoodProductRespVO respVO = ConvertUtils.convert(foodProduct,FoodProductRespVO.class);
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
    public ResultVO update(@RequestBody FoodProductUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        FoodProduct foodProduct = ConvertUtils.convert(reqVO,FoodProduct.class);
        foodProduct.setUpdatedBy(0L);
        Date now = new Date();
        foodProduct.setUpdatedAt(now);
        int i = foodProductService.updateByPrimaryKeySelective(foodProduct);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        FoodProductRespVO respVO = ConvertUtils.convert(foodProduct,FoodProductRespVO.class);
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
    public ResultVO update(@RequestBody FoodProductDeleteReqVO reqVO) {
        //通过传入foodId删除其对应的所有数据
        if(reqVO.getFoodId()==null || reqVO.getFoodId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = foodProductService.deleteByPrimaryKey(reqVO.getFoodId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(FoodProductReqVO reqVO) {
        if (reqVO.getFoodId() == null || reqVO.getFoodId() == 0L) {
            throw new BizException("foodId cannot be empty!");
        }
        if (reqVO.getProductId() == null || reqVO.getProductId() == 0L) {
            throw new BizException("productId cannot be empty!");
        }
    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(FoodProductUpdateReqVO reqVO){
        if(reqVO.getFoodId()==null || reqVO.getFoodId()==0L){
            throw new BizException("foodId cannot be empty!");
        }
        if(reqVO.getProductId()==null || reqVO.getProductId()==0L){
            throw new BizException("productId cannot be empty!");
        }

    }

}
