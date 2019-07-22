package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.WineVintageScore;

public interface WineVintageScoreMapper extends Mapper<WineVintageScore> {
    int insert(WineVintageScore record);

    int insertSelective(WineVintageScore record);
}