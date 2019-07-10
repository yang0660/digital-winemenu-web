package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.FoodType;
import com.myicellar.digitalmenu.dao.mapper.FoodTypeMapperExt;
import com.myicellar.digitalmenu.vo.request.FoodTypePageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FoodTypeService extends BaseService<Long, FoodType, FoodTypeMapperExt> {

    /**
     * 列表查询-分页
     * @return
     */
    public PageResponseVO<FoodType> queryPageList(FoodTypePageReqVO reqVO){
        PageResponseVO<FoodType> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);

        return page;
    }

    /**
     * 列表查询-分页
     * @return
     */
    public List<FoodType> queryListBysupplierId(Long supplierId){
        return mapper.selectListBySupplierId(supplierId);
    }
}