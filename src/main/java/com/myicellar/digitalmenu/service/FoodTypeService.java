package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.dao.mapper.FoodTypeMapperExt;
import com.myicellar.digitalmenu.vo.request.FoodTypePageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FoodTypeService extends BaseService<Long, FoodType, FoodTypeMapperExt> {

    /**
     * 查询用户列表(分页，支持模糊匹配)
     * @return
     */
    public PageResponseVO<FoodType> queryPageList(FoodTypePageReqVO reqVO){
        PageResponseVO<FoodType> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        return page;
    }
}