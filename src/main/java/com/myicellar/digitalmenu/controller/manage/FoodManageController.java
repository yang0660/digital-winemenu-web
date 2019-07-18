package com.myicellar.digitalmenu.controller.manage;

import com.aliyuncs.utils.StringUtils;
import com.myicellar.digitalmenu.dao.entity.Food;
import com.myicellar.digitalmenu.service.FoodService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.FoodDeleteReqVO;
import com.myicellar.digitalmenu.vo.request.FoodPageReqVO;
import com.myicellar.digitalmenu.vo.request.FoodReqVO;
import com.myicellar.digitalmenu.vo.request.FoodUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.FoodRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
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

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/manage/food")
@Api(tags = "美食", description = "/manage/food")
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
     * 新增
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/add")
    @AuthIgnore
    @ApiOperation("新增")
    public ResultVO<FoodRespVO> add(@RequestBody FoodReqVO reqVO) {
        //参数校验
        checkNewParam(reqVO);
        Food food = ConvertUtils.convert(reqVO,Food.class);
        food.setFoodId(snowflakeIdWorker.nextId());
        food.setCreatedBy(0L);
        food.setUpdatedBy(0L);
        Date now = new Date();
        food.setCreatedAt(now);
        food.setUpdatedAt(now);
        int i = foodService.insertSelective(food);
        if(i==0){
            return ResultVO.validError("save is failed!");
        }

        FoodRespVO respVO = ConvertUtils.convert(food,FoodRespVO.class);
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
    public ResultVO update(@RequestBody FoodUpdateReqVO reqVO) {
        //参数校验
        checkUpdateParam(reqVO);
        Food food = ConvertUtils.convert(reqVO,Food.class);
        food.setUpdatedBy(0L);
        Date now = new Date();
        food.setUpdatedAt(now);
        int i = foodService.updateByPrimaryKeySelective(food);
        if(i==0){
            return ResultVO.validError("update is failed!");
        }

        FoodRespVO respVO = ConvertUtils.convert(food,FoodRespVO.class);
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
    public ResultVO update(@RequestBody FoodDeleteReqVO reqVO) {
        if(reqVO.getFoodId()==null || reqVO.getFoodId()==0L){
            return ResultVO.validError("parameter is invalid！");
        }
        int i = foodService.deleteByPrimaryKey(reqVO.getFoodId());
        if(i==0){
            return ResultVO.validError("delete is failed!");
        }

        return ResultVO.success("delete is success!");
    }

    /**
     * 校验新增参数
     * @param reqVO
     */
    private void checkNewParam(FoodReqVO reqVO){
        if(reqVO.getFoodTypeId()==null || reqVO.getFoodTypeId()==0L){
            throw new BizException("supplier cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getFoodNameEng())){
            throw new BizException("foodNameEng cannot be empty!");
        }
    }

    /**
     * 校验修改参数
     * @param reqVO
     */
    private void checkUpdateParam(FoodUpdateReqVO reqVO){
        if(reqVO.getFoodId()==null || reqVO.getFoodId()==0L){
            throw new BizException("foodId cannot be empty!");
        }
        if(StringUtils.isEmpty(reqVO.getFoodNameEng())){
            throw new BizException("foodNameEng cannot be empty!");
        }
    }

}
