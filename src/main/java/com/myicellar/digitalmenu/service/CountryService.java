package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Country;
import com.myicellar.digitalmenu.dao.mapper.CountryMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CountryService extends BaseService<Long, Country, CountryMapperExt> {


    /**
     * 列表查询
     * @return
     */
    public List<Country> queryList(){
        List<Country> list=mapper.selectList();
        return list;
    }

}