package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Attr;

import java.util.List;

public interface AttrMapperExt extends AttrMapper{

    List<Attr> selectList();

    List<Attr> queryListBySupplierId(Long supplierId);
}