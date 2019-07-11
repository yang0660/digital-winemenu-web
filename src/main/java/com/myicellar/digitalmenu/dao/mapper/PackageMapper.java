package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.Package;

public interface PackageMapper {
    int deleteByPrimaryKey(Long packageId);

    int insert(Package record);

    int insertSelective(Package record);

    Package selectByPrimaryKey(Long packageId);

    int updateByPrimaryKeySelective(Package record);

    int updateByPrimaryKey(Package record);
}