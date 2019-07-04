package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Winery;

public interface WineryMapper extends Mapper<Winery> {
    int insert(Winery record);

    int insertSelective(Winery record);
}