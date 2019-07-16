package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.WineType;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface WineTypeMapperExt extends WineTypeMapper {
    List<WineType> selectList();

    List<WineType> selectListBySupplierId(Long supplierId);

    @MapKey("wineTypeNameEng")
    Map<String,WineType> selectNameMap();
}