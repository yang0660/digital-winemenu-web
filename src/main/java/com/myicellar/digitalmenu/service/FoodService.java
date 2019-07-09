package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Food;
import com.myicellar.digitalmenu.dao.mapper.FoodMapperExt;
import com.myicellar.digitalmenu.vo.request.FoodDetailReqVO;
import com.myicellar.digitalmenu.vo.request.FoodDisplayReqVO;
import com.myicellar.digitalmenu.vo.request.FoodPageReqVO;
import com.myicellar.digitalmenu.vo.response.FoodDetailRespVO;
import com.myicellar.digitalmenu.vo.response.FoodDisplayRespVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FoodService extends BaseService<Long, Food, FoodMapperExt> {

    /**
     * 列表查询-分页
     * @return
     */
    public PageResponseVO<Food> queryPageList(FoodPageReqVO reqVO){
        PageResponseVO<Food> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);
        return page;
    }

    /**
     * 美食列表查询-根据美食分类id
     * @return
     */
    public List<FoodDisplayRespVO> queryListByFoodTypeId(FoodDisplayReqVO reqVO){
        List<FoodDisplayRespVO> page = mapper.queryListByFoodTypeId(reqVO);
        return page;
    }

    /**
     * 美食详情查询-根据美食id
     * @return
     */
    public FoodDetailRespVO queryListByFoodId(FoodDetailReqVO reqVO){
        FoodDetailRespVO page = mapper.queryListByFoodId(reqVO);
        return page;
    }





}