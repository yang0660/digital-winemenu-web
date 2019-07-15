package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.WineVintageAttr;
import org.apache.ibatis.annotations.Param;

public interface WineVintageAttrMapper extends Mapper<WineVintageAttr> {
    int deleteByPrimaryKey(@Param("wineId") Long wineId, @Param("vintageTag") Long vintageTag, @Param("attrId") Long attrId);

    int insert(WineVintageAttr record);

    int insertSelective(WineVintageAttr record);

    WineVintageAttr selectByPrimaryKey(@Param("wineId") Long wineId, @Param("vintageTag") Long vintageTag, @Param("attrId") Long attrId);

    int updateByPrimaryKeySelective(WineVintageAttr record);

    int updateByPrimaryKey(WineVintageAttr record);
}