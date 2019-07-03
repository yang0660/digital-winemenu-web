package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.ImgType;

public interface ImgTypeMapper {
    int deleteByPrimaryKey(Long imgTypeId);

    int insert(ImgType record);

    int insertSelective(ImgType record);

    ImgType selectByPrimaryKey(Long imgTypeId);

    int updateByPrimaryKeySelective(ImgType record);

    int updateByPrimaryKey(ImgType record);
}