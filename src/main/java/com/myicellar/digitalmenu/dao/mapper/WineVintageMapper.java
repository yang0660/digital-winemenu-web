package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.WineVintage;
import org.apache.ibatis.annotations.Param;

public interface WineVintageMapper extends Mapper<WineVintage>{
    int deleteByPrimaryKey(@Param("wineId") Long wineId, @Param("vintageTag") Long vintageTag);

    int insert(WineVintage record);

    int insertSelective(WineVintage record);

    WineVintage selectByPrimaryKey(@Param("wineId") Long wineId, @Param("vintageTag") Long vintageTag);

    int updateByPrimaryKeySelective(WineVintage record);

    int updateByPrimaryKeyWithBLOBs(WineVintage record);

    int updateByPrimaryKey(WineVintage record);
}