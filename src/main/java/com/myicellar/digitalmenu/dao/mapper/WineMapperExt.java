package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Wine;
import com.myicellar.digitalmenu.vo.request.WinePageReqVO;
import com.myicellar.digitalmenu.vo.response.WineRespVO;

import java.util.List;

public interface WineMapperExt extends WineMapper{
    long selectCount(WinePageReqVO reqVO);

    List<WineRespVO> selectList(WinePageReqVO reqVO);

    Wine selectByName(String wineNameEng);

    WineRespVO selectByWineId(Long wineId);

    Wine selectByWineryId(Long wineryId);

}