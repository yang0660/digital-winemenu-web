package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.vo.response.WineAttrMapRespVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WineVintageAttrMapperExt extends WineVintageAttrMapper {

    @MapKey("wineVintageId")
    Map<String,WineAttrMapRespVO> selectAttrMapByWineVintageIds(@Param("attrCatgId") Long attrCatgId,
                                                       @Param("wineVintageIds") List<String> wineVintageIds);

    Integer deleteByWineIdAndVintage(@Param("wineId") Long wineId,
                                       @Param("vintageTag") Long vintageTag);
}