package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.Restaurant;
import com.myicellar.digitalmenu.service.RestaurantService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.BizException;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import com.myicellar.digitalmenu.vo.request.RestaurantListReqVO;
import com.myicellar.digitalmenu.vo.request.RestaurantReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.RestaurantListRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/manage/restaurant/")
@Api(tags = "餐厅管理", description = "/manage/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 详情查询-根据餐厅ID
     *
     * @param restaurantId
     * @return
     * @throws
     * @author daizhiyue
     * @date 2019-01-06
     * @since
     */
    @PostMapping(value = "/queryById")
    @ApiOperation(value = "详情查询-根据餐厅ID")
    @AuthIgnore
    public ResultVO<RestaurantListReqVO> queryById(@RequestBody Long restaurantId) {
        Restaurant restaurant = restaurantService.selectByPrimaryKey(restaurantId);
        RestaurantListReqVO respVO =  new RestaurantListReqVO();
        if(respVO!=null){
            respVO = ConvertUtils.convert(restaurant,RestaurantListReqVO.class);
        }
        ResultVO  result = ResultVO.success("查询成功");
        result.setData(respVO);

        return result;
    }

    /**
     * 查询餐厅列表-下拉框选项
     *
     * @return
     * @throws
     * @author daizhiyue
     * @date 2019-01-06
     * @since
     */
    @PostMapping(value = "/queryList")
    @ApiOperation(value = "查询餐厅列表-下拉框选项")
    @AuthIgnore
    public ResultVO<List<RestaurantListRespVO>> queryList() {
        List<Restaurant>  list = restaurantService.queryList();
        List<RestaurantListRespVO> resultList = new ArrayList<RestaurantListRespVO>();
        if(!CollectionUtils.isEmpty(list)) {
            resultList = ConvertUtils.convert(list, RestaurantListRespVO.class);
        }
        ResultVO  result = ResultVO.success("查询成功");
        result.setData(resultList);

        return result;
    }

    /**
     * 查询餐厅列表-根据餐厅名称(分页，支持模糊匹配)
     *
     * @param reqVO
     * @return
     * @throws
     * @author daizhiyue
     * @date 2019-01-06
     * @since
     */
    @PostMapping(value = "/queryListPage")
    @ApiOperation(value = "查询餐厅列表-根据餐厅名称(分页，支持模糊匹配)")
    @AuthIgnore
    public ResultVO<PageResponseVO<RestaurantListRespVO>> queryListPage(@RequestBody RestaurantListReqVO reqVO) {
        if(!StringUtils.isEmpty(reqVO.getRestaurantName())){
            reqVO.setRestaurantName(reqVO.getRestaurantName().trim());
        }

        PageResponseVO<Restaurant>  page = restaurantService.queryRestaurantListPage(reqVO);
        PageResponseVO<RestaurantListRespVO> resultPage = new PageResponseVO<RestaurantListRespVO>();
        if(page!=null && page.getItems()!=null) {
            resultPage = ConvertUtils.convertPage(page, RestaurantListRespVO.class);
        }
        ResultVO  result = ResultVO.success("查询成功");
        result.setData(resultPage);

        return result;
    }

    /**
     * 新增餐厅-餐厅名称不能重复
     *
     * @param
     * @return
     * @throws
     * @author daizhiyue
     * @date 2019-01-06
     * @since
     */
    @PostMapping(value = "/save")
    @ApiOperation(value = "新增餐厅-餐厅名称不能重复")
    @AuthIgnore
    public ResultVO<Restaurant> save(@RequestBody RestaurantReqVO reqVo) {
        Restaurant restaurant =ConvertUtils.convert(reqVo, Restaurant.class);
        if (StringUtils.isEmpty(reqVo.getRestaurantNameChs()) && StringUtils.isEmpty(reqVo.getRestaurantNameCht()) && StringUtils.isEmpty(reqVo.getRestaurantNameEng())){
            ResultVO resultVO = ResultVO.validError("餐厅名称不能为空!");
            return resultVO;
        }
        Restaurant tmp = restaurantService.selectByRestaurantNames(reqVo);
        if(tmp!=null){
            ResultVO resultVO = ResultVO.validError("餐厅名称已存在!");
            return resultVO;
        }

        restaurant.setRestaurantId(snowflakeIdWorker.nextId());
        Date now = new Date();
        restaurant.setCreateTime(now);
        restaurant.setUpdateTime(now);
        restaurant.setCreateUser(1L);
        restaurant.setUpdateUser(1L);
        restaurant.setStatus((byte) 0);
        int resp= restaurantService.insertSelective(restaurant);
        ResultVO  result = ResultVO.success("新增成功");
        result.setData(resp);

        return result;
    }


    /**
     * 修改餐厅-餐厅名称不能重复
     *
     * @param
     * @return
     * @throws
     * @author daizhiyue
     * @date 2019-01-06
     * @since
     */
    @PostMapping(value = "/update")
    @ApiOperation(value = "修改餐厅-餐厅名称不能重复")
    @AuthIgnore
    public ResultVO<Restaurant> update(@RequestBody RestaurantReqVO reqVo) {
        Restaurant restaurant =ConvertUtils.convert(reqVo, Restaurant.class);
        if (StringUtils.isEmpty(reqVo.getRestaurantNameChs()) && StringUtils.isEmpty(reqVo.getRestaurantNameCht()) && StringUtils.isEmpty(reqVo.getRestaurantNameEng())){
            ResultVO resultVO = ResultVO.validError("餐厅名称不能为空!");
            return resultVO;
        }
        Restaurant tmp = restaurantService.selectByRestaurantNames(reqVo);
        if(tmp!=null && tmp.getRestaurantId()!=reqVo.getRestaurantId()){
            ResultVO resultVO = ResultVO.validError("餐厅名称已存在!");
            return resultVO;
        }

        Date now = new Date();
        restaurant.setUpdateTime(now);
        int resp= restaurantService.updateByPrimaryKeySelective(restaurant);
        ResultVO  result = ResultVO.success("修改成功");
        result.setData(resp);

        return result;
    }


}
