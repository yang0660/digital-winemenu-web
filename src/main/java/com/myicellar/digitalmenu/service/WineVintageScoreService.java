package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.WineVintageScore;
import com.myicellar.digitalmenu.dao.mapper.WineVintageScoreMapperExt;
import com.myicellar.digitalmenu.vo.request.WineVintageScorePageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WineVintageScoreService extends BaseService<Long, WineVintageScore, WineVintageScoreMapperExt> {

    /**
     * 列表查询-分页
     * @return
     */
    public PageResponseVO<WineVintageScore> queryPageList(WineVintageScorePageReqVO reqVO){
        PageResponseVO<WineVintageScore> page = selectPage(reqVO, mapper::selectCount, mapper::selectList);
        return page;
    }




}