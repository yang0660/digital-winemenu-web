package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.WineAttr;
import com.myicellar.digitalmenu.dao.mapper.WineAttrMapperExt;
import com.myicellar.digitalmenu.vo.request.WineAttrPageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WineAttrService extends BaseService<Long, WineAttr, WineAttrMapperExt> {


    /**
     * 列表查询-分页
     * @return
     */
    public PageResponseVO<WineAttr> queryPageList(WineAttrPageReqVO reqVO){
        PageResponseVO<WineAttr> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);
        return page;
    }

}