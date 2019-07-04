package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Winery;

import java.util.List;

public interface WineryMapperExt extends WineryMapper {
    List<Winery> selectList();

}