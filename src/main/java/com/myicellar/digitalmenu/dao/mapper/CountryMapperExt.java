package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Country;
import com.myicellar.digitalmenu.vo.response.CountryRespVO;

import java.util.List;

public interface CountryMapperExt extends CountryMapper {

    List<Country> selectList();

    List<CountryRespVO> selectListBySupplierId(Long supplierId);
}