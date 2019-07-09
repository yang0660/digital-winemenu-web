package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.WineVintage;
import com.myicellar.digitalmenu.dao.mapper.WineVintageMapperExt;
import com.myicellar.digitalmenu.vo.request.WineVintagePageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WineVintageService extends BaseService<Long, WineVintage, WineVintageMapperExt> {

    /**
     * 列表查询-分页
     * @return
     */
    public PageResponseVO<WineVintage> queryPageList(WineVintagePageReqVO reqVO){
        PageResponseVO<WineVintage> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);
        return page;
    }




}