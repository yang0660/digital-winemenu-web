package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Food;
import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.dao.mapper.FoodMapperExt;
import com.myicellar.digitalmenu.dao.mapper.FoodTypeMapperExt;
import com.myicellar.digitalmenu.vo.request.FoodPageReqVO;
import com.myicellar.digitalmenu.vo.request.FoodTypePageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}