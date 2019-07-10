package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.vo.response.WineAttrMapRespVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WineAttrMapperExt extends WineAttrMapper{

    @MapKey("wineId")
    Map<Long,WineAttrMapRespVO> selectAttrMapByWineIds(@Param("attrCatgId") Long attrCatgId,
                                                   @Param("wineIds") List<Long> wineIds);
}