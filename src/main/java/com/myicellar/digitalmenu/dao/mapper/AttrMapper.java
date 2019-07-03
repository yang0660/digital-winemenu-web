package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Attr;

public interface AttrMapper {
    int deleteByPrimaryKey(Long attrId);

    int insert(Attr record);

    int insertSelective(Attr record);

    Attr selectByPrimaryKey(Long attrId);

    int updateByPrimaryKeySelective(Attr record);

    int updateByPrimaryKey(Attr record);
}