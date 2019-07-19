package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.vo.request.WineDetailReqVO;
import com.myicellar.digitalmenu.vo.response.WineVintageListRespVO;

import java.util.List;

public interface WineVintageMapperExt extends WineVintageMapper{
    Long selectCount(WineDetailReqVO reqVO);

    List<WineVintageListRespVO> selectList(WineDetailReqVO reqVO);
}