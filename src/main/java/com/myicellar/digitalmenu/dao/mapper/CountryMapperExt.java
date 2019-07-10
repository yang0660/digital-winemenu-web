package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Country;

import java.util.List;

public interface CountryMapperExt extends CountryMapper{

    List<Country> selectList();

    List<Country> selectListBySupplierId(Long supplierId);
}