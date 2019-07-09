package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.WineVintage;
import com.myicellar.digitalmenu.vo.request.WineVintagePageReqVO;

import java.util.List;

public interface WineVintageMapperExt extends WineVintageMapper{
    long selectCount(WineVintagePageReqVO reqVO);

    List<WineVintage> selectList(WineVintagePageReqVO reqVO);
}