package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Origin;
import com.myicellar.digitalmenu.dao.mapper.OriginMapperExt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OriginService extends BaseService<Long, Origin, OriginMapperExt> {


    /**
     * 列表查询
     * @return
     */
    public List<Origin> queryList(){
        List<Origin> list=mapper.selectList();
        return list;
    }

}