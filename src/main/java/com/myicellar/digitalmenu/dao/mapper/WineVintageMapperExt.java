package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.vo.request.WineVintageListReqVO;
import com.myicellar.digitalmenu.vo.response.VintageRespVO;
import com.myicellar.digitalmenu.vo.response.WineVintageListRespVO;

import java.util.List;

public interface WineVintageMapperExt extends WineVintageMapper {
    Long selectCount(WineVintageListReqVO reqVO);

    List<WineVintageListRespVO> selectList(WineVintageListReqVO reqVO);

    List<VintageRespVO> selectVintageList(Long wineId);
}