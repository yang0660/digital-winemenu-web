package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Winery;

public interface WineryMapper extends Mapper<Winery> {
    int deleteByPrimaryKey(Long wineryId);

    int insert(Winery record);

    int insertSelective(Winery record);

    Winery selectByPrimaryKey(Long wineryId);

    int updateByPrimaryKeySelective(Winery record);

    int updateByPrimaryKeyWithBLOBs(Winery record);

    int updateByPrimaryKey(Winery record);
}