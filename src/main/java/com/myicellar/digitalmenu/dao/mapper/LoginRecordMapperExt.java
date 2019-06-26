package com.myicellar.digitalmenu.dao.mapper;

public interface LoginRecordMapperExt extends LoginRecordMapper {

    int selectCountByUserId(Long userId);
}