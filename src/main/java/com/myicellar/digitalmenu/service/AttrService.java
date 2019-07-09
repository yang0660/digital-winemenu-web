package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Attr;
import com.myicellar.digitalmenu.dao.mapper.AttrMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AttrService extends BaseService<Long, Attr, AttrMapperExt> {


    /**
     * 列表查询
     * @return
     */
    public List<Attr> queryList(){
        List<Attr> list=mapper.selectList();
        return list;
    }
}