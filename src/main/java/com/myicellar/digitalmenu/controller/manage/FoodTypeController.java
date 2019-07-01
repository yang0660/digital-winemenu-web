package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.dao.entity.Restaurant;
import com.myicellar.digitalmenu.service.FoodTypeService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.FoodTypeListReqVO;
import com.myicellar.digitalmenu.vo.request.FoodTypeReqVO;
import com.myicellar.digitalmenu.vo.request.RestaurantListReqVO;
import com.myicellar.digitalmenu.vo.request.RestaurantReqVO;
import com.myicellar.digitalmenu.vo.response.FoodTypeRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/manage/foodtype/")
@Api(tags = "食品管理", description = "/manage/foodtype")
public class FoodTypeController {

    @Autowired
    private FoodTypeService foodTypeService;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 美食详情查询-根据美食分类ID
     *
     * @param foodtypeId
     * @return
     * @throws
     * @author daizhiyue
     * @date 2019-01-06
     * @since
     */
    @PostMapping(value = "/queryById")
    @ApiOperation(value = "美食详情查询-根据美食分类ID ")
    @AuthIgnore
    public ResultVO<FoodTypeService> queryById(@RequestBody Long foodtypeId) {
        FoodType foodType = foodTypeService.selectByPrimaryKey(foodtypeId);
        FoodTypeListReqVO respVO =  new FoodTypeListReqVO();
        if(respVO!=null){
            respVO = ConvertUtils.convert(foodType,FoodTypeListReqVO.class);
        }
        ResultVO  result = ResultVO.success("查询成功成功");
        result.setData(foodType);

        return result;
    }

    /**
     * 新增美食-美食名称不能重复
     *
     * @param
     * @return
     * @throws
     * @author daizhiyue
     * @date 2019-01-06
     * @since
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增美食-美食名称不能重复")
    @AuthIgnore
    public ResultVO<FoodType> save(@RequestBody FoodTypeReqVO reqVo) {
        FoodType foodType  = ConvertUtils.convert(reqVo, FoodType.class);
        if (StringUtils.isEmpty(reqVo.getFoodTypeNameChs()) && StringUtils.isEmpty(reqVo.getFoodTypeNameCht()) && StringUtils.isEmpty(reqVo.getFoodTypeNameEng())){
            ResultVO resultVO = ResultVO.validError("美食名称不能为空!");
            return resultVO;
        }
        FoodType tmp = foodTypeService.selectByFoodTypeNames(reqVo);
        if(tmp!=null){
            ResultVO resultVO = ResultVO.validError("美食名称已存在!");
            return resultVO;
        }


        Date now = new Date();
        foodType.setRestaurantId(reqVo.getRestaurantId());
        foodType.setCreateTime(now);
        foodType.setUpdateTime(now);
        foodType.setCreateUser(1L);
        foodType.setUpdateUser(1L);
        int resp= foodTypeService.insertSelective(foodType);
        ResultVO  result = ResultVO.success("新增成功");
        result.setData(resp);

        return result;
    }


    /**
     * 修改美食-美食名称不能重复
     *
     * @param
     * @return
     * @throws
     * @author daizhiyue
     * @date 2019-01-06
     * @since
     */
    @PostMapping(value = "/update")
    @ApiOperation(value = "修改美食-美食名称不能重复")
    @AuthIgnore
    public ResultVO<FoodType> update(@RequestBody FoodTypeReqVO reqVo) {
        FoodType foodType =ConvertUtils.convert(reqVo, FoodType.class);
        if (StringUtils.isEmpty(reqVo.getFoodTypeNameChs()) && StringUtils.isEmpty(reqVo.getFoodTypeNameCht()) && StringUtils.isEmpty(reqVo.getFoodTypeNameEng())){
            ResultVO resultVO = ResultVO.validError("美食名称不能为空!");
            return resultVO;
        }
        FoodType tmp = foodTypeService.selectByFoodTypeNames(reqVo);
        if(tmp!=null && tmp.getFoodTypeId()!=reqVo.getFoodTypeId()){
            ResultVO resultVO = ResultVO.validError("美食名称已存在!");
            return resultVO;
        }

        Date now = new Date();
        foodType.setUpdateTime(now);
        int resp= foodTypeService.updateByPrimaryKeySelective(foodType);
        ResultVO  result = ResultVO.success("修改成功");
        result.setData(resp);

        return result;
    }


}
