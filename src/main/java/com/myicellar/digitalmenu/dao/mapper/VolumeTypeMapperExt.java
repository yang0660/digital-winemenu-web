package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.VolumeType;

import java.util.List;

public interface VolumeTypeMapperExt extends VolumeTypeMapper{
    List<VolumeType> selectList();

}