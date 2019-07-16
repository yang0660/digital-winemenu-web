package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.WineType;
import com.myicellar.digitalmenu.dao.mapper.WineTypeMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WineTypeService extends BaseService<Long, WineType, WineTypeMapperExt> {


    /**
     * 列表查询
     * @return
     */
    public List<WineType> queryList(){
        return mapper.selectList();
    }

    /**
     * 查询酒品分类列表（根据供应商ID）
     * @return
     */
    public List<WineType> queryListBySupplierId(Long supplierId){
        return mapper.selectListBySupplierId(supplierId);
    }

    public Map<String,WineType> queryNameMap(){
        return mapper.selectNameMap();
    }

}