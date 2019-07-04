package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Origin;

import java.util.List;

public interface OriginMapperExt extends OriginMapper{
    List<Origin> selectList();

}