package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.VolumeType;

public interface VolumeTypeMapper extends Mapper<VolumeType>{
    int deleteByPrimaryKey(Long volumeTypeId);

    int insert(VolumeType record);

    int insertSelective(VolumeType record);

    VolumeType selectByPrimaryKey(Long volumeTypeId);

    int updateByPrimaryKeySelective(VolumeType record);

    int updateByPrimaryKey(VolumeType record);
}