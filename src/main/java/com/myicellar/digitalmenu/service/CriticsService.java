package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Critics;
import com.myicellar.digitalmenu.dao.mapper.CriticsMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CriticsService extends BaseService<Long, Critics, CriticsMapperExt> {


    /**
     * 列表查询
     * @return
     */
    public List<Critics> queryList(){
        List<Critics> list=mapper.selectList();
        return list;
    }

}