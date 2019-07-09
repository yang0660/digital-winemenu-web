package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.WineAttr;
import com.myicellar.digitalmenu.vo.request.WineAttrPageReqVO;

import java.util.List;

public interface WineAttrMapperExt extends WineAttrMapper{
    long selectCount(WineAttrPageReqVO reqVO);

    List<WineAttr> selectList(WineAttrPageReqVO reqVO);
}