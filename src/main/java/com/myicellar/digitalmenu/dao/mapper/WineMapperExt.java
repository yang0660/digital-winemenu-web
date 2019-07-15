package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Wine;
import com.myicellar.digitalmenu.vo.request.WinePageReqVO;
import com.myicellar.digitalmenu.vo.request.WineTypeReqVO;

import java.util.List;

public interface WineMapperExt extends WineMapper{
    long selectCount(WinePageReqVO reqVO);

    List<Wine> selectList(WinePageReqVO reqVO);

    List<Wine> selectDropList(WineTypeReqVO reqVO);

    Wine selectByWineryId(Long wineryId);

}