package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.WineType;

import java.util.List;

public interface WineTypeMapperExt extends WineTypeMapper {
    List<WineType> selectList();

    List<WineType> selectListBySupplierId(Long supplierId);
}