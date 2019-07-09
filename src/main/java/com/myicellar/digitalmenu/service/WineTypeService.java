package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.WineType;
import com.myicellar.digitalmenu.dao.mapper.WineTypeMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WineTypeService extends BaseService<Long, WineType, WineTypeMapperExt> {


    /**
     * 列表查询
     * @return
     */
    public List<WineType> queryList(){
        List<WineType> list=mapper.selectList();
        return list;
    }

}