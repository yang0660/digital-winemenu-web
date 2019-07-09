package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Critics;

import java.util.List;

public interface CriticsMapperExt extends CriticsMapper{


    List<Critics> selectList();
}