package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.WineVintageScore;

public interface WineVintageScoreMapper {
    int deleteByPrimaryKey(Long vintageScoreId);

    int insert(WineVintageScore record);

    int insertSelective(WineVintageScore record);

    WineVintageScore selectByPrimaryKey(Long vintageScoreId);

    int updateByPrimaryKeySelective(WineVintageScore record);

    int updateByPrimaryKeyWithBLOBs(WineVintageScore record);

    int updateByPrimaryKey(WineVintageScore record);
}