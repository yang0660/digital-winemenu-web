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
     * 查询原料列表（根据供应商ID）
     * @return
     */
    public List<Attr> queryListBySupplierId(Long supplierId){
        List<Attr> list=mapper.queryListBySupplierId(supplierId);
        return list;
    }
}