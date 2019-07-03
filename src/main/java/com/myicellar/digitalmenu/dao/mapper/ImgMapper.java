package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Img;

public interface ImgMapper extends Mapper<Img> {
    int deleteByPrimaryKey(Long imgId);

    int insert(Img record);

    int insertSelective(Img record);

    Img selectByPrimaryKey(Long imgId);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKey(Img record);
}