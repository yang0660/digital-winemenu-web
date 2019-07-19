package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.WineVintage;
import com.myicellar.digitalmenu.dao.mapper.WineVintageMapperExt;
import com.myicellar.digitalmenu.vo.request.WineDetailReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.WineVintageListRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WineVintageService extends BaseService<Long, WineVintage, WineVintageMapperExt> {

    public PageResponseVO<WineVintageListRespVO> queryPageList(WineDetailReqVO reqVO){
        PageResponseVO<WineVintageListRespVO> page = selectPage(reqVO,mapper:: selectCount, mapper:: selectList);

        return page;
    }
}