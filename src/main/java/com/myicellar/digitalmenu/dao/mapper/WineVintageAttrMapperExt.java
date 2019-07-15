package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.WineVintageAttr;
import com.myicellar.digitalmenu.vo.response.WineAttrMapRespVO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WineVintageAttrMapperExt extends WineVintageAttrMapper {

    @MapKey("wineVintageId")
    Map<String,WineAttrMapRespVO> selectAttrMapByWineIds(@Param("attrCatgId") Long attrCatgId,
                                                       @Param("wineVintageIds") List<String> wineVintageIds);
}