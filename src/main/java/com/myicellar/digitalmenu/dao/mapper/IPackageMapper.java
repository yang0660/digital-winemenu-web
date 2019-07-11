package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.IPackage;

public interface IPackageMapper extends Mapper<IPackage> {
    int deleteByPrimaryKey(Long packageId);

    int insert(IPackage record);

    int insertSelective(IPackage record);

    IPackage selectByPrimaryKey(Long packageId);

    int updateByPrimaryKeySelective(IPackage record);

    int updateByPrimaryKey(IPackage record);
}