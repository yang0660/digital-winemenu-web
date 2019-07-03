package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.VolumeType;

public interface VolumeTypeMapper {
    int deleteByPrimaryKey(Integer volumeTypeId);

    int insert(VolumeType record);

    int insertSelective(VolumeType record);

    VolumeType selectByPrimaryKey(Integer volumeTypeId);

    int updateByPrimaryKeySelective(VolumeType record);

    int updateByPrimaryKeyWithBLOBs(VolumeType record);

    int updateByPrimaryKey(VolumeType record);
}