package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Attr;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface AttrMapperExt extends AttrMapper{

    List<Attr> selectList();

    List<Attr> selectListBySupplierId(Long supplierId);

    List<Attr> selectListByCatgId(Long attrCatgId);

    @MapKey("attrNameEng")
    Map<String,Attr> selectNameMap();
}