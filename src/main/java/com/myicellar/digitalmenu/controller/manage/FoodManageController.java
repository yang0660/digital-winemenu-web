package com.myicellar.digitalmenu.controller.manage;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.Food;
import com.myicellar.digitalmenu.service.FoodService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.*;
import com.myicellar.digitalmenu.vo.response.FoodDetailBGRespVO;
import com.myicellar.digitalmenu.vo.response.FoodRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
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
@RequestMapping("/manage/food")
@Api(tags = "美食管理-美食", description = "/manage/food")
public class FoodManageController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 列表查询
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryListPage")
    @AuthIgnore
    @ApiOperation("列表查询")
    public ResultVO<PageResponseVO<FoodRespVO>> queryListPage(@RequestBody FoodPageReqVO reqVO) {
        PageResponseVO<FoodRespVO> page = foodService.queryPageList(reqVO);
        return ResultVO.success(page);
    }

    /**
     * 后台美食详情
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/queryFoodDetail")
    @AuthIgnore
    @ApiOperation("后台美食详情")
    public ResultVO<FoodDetailBGRespVO> queryFoodDetail(@RequestBody FoodDetailReqVO reqVO) {
        FoodDetailBGRespVO result = foodService.queryFoodDetail(reqVO.getFoodId());
        return ResultVO.success(result);
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
    public ResultVO<Integer> add(@RequestBody FoodReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Food food = ConvertUtils.convert(reqVO, Food.class);
        food.setFoodId(snowflakeIdWorker.nextId());
        food.setCreatedBy(0L);
        food.setUpdatedBy(0L);
        Date now = new Date();
        food.setCreatedAt(now);
        food.setUpdatedAt(now);
        return ResultVO.success(foodService.insertSelective(food));
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
    public ResultVO<Integer> update(@RequestBody FoodUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Food food = ConvertUtils.convert(reqVO, Food.class);
        food.setUpdatedBy(0L);
        Date now = new Date();
        food.setUpdatedAt(now);
        return ResultVO.success(foodService.updateByPrimaryKeySelective(food));
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
    public ResultVO<Integer> update(@RequestBody FoodDeleteReqVO reqVO) {
        return ResultVO.success(foodService.deleteByPrimaryKey(reqVO.getFoodId()));
    }

    /**
     * 校验新增参数
     *
     * @param reqVO
     */
    private void checkNewParam(FoodReqVO reqVO) {
        if (reqVO.getSupplierId() == null || reqVO.getSupplierId() == 0L) {
            throw new BizException("supplierId cannot be empty!");
        }
        if (reqVO.getFoodTypeId() == null || reqVO.getFoodTypeId() == 0L) {
            throw new BizException("foodTypeId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getFoodNameEng())) {
            throw new BizException("foodNameEng cannot be empty!");
        }
        if (reqVO.getFoodImgId() == null || reqVO.getFoodImgId() == 0L) {
            throw new BizException("foodImgId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getNotePlainEng())) {
            throw new BizException("foodNameEng cannot be empty!");
        }
        if (reqVO.getPrice() == null) {
            throw new BizException("price cannot be empty!");
        }
        if (reqVO.getIsRecommend() == null) {
            throw new BizException("isRecommend cannot be empty!");
        }
        if (foodService.queryFoodName(reqVO.getFoodNameEng()) != null) {
            throw new BizException("Food already exist!");
        }
    }

    /**
     * 校验修改参数
     *
     * @param reqVO
     */
    private void checkUpdateParam(FoodUpdateReqVO reqVO) {
        if (reqVO.getSupplierId() == null || reqVO.getSupplierId() == 0L) {
            throw new BizException("supplierId cannot be empty!");
        }
        if (reqVO.getFoodTypeId() == null || reqVO.getFoodTypeId() == 0L) {
            throw new BizException("foodTypeId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getFoodNameEng())) {
            throw new BizException("foodNameEng cannot be empty!");
        }
        if (reqVO.getFoodImgId() == null || reqVO.getFoodImgId() == 0L) {
            throw new BizException("foodImgId cannot be empty!");
        }
        if (StringUtils.isEmpty(reqVO.getNotePlainEng())) {
            throw new BizException("foodNameEng cannot be empty!");
        }
        if (reqVO.getPrice() == null) {
            throw new BizException("price cannot be empty!");
        }
        if (reqVO.getIsRecommend() == null) {
            throw new BizException("isRecommend cannot be empty!");
        }
        if (foodService.queryFoodName(reqVO.getFoodNameEng()) != null) {
            if (foodService.selectByPrimaryKey(reqVO.getFoodId()) != null) {
                throw new BizException("Food already exist!");
            }

        }
    }
}
