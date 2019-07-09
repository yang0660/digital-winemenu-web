package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.WineAttr;
import org.apache.ibatis.annotations.Param;

public interface WineAttrMapper extends Mapper<WineAttr>{
    int deleteByPrimaryKey(@Param("attrId") Long attrId, @Param("wineId") Long wineId);

    int insert(WineAttr record);

    int insertSelective(WineAttr record);

    WineAttr selectByPrimaryKey(@Param("attrId") Long attrId, @Param("wineId") Long wineId);

    int updateByPrimaryKeySelective(WineAttr record);

    int updateByPrimaryKey(WineAttr record);
}