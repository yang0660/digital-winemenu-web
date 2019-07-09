package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.WineVintageScore;
import com.myicellar.digitalmenu.vo.request.WineVintageScorePageReqVO;

import java.util.List;

public interface WineVintageScoreMapperExt extends WineVintageScoreMapper{
    long selectCount(WineVintageScorePageReqVO reqVO);

    List<WineVintageScore> selectList(WineVintageScorePageReqVO reqVO);
}