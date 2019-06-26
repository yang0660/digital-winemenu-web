package com.myicellar.digitalmenu.dao.mapper;

import com.myicellar.digitalmenu.dao.entity.SmsRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SmsRecordMapperExt extends SmsRecordMapper {

    SmsRecord selectLastByMobileAndType(@Param("mobile") String mobile, @Param("type") Byte type);

    List<SmsRecord> selectTodayRecordsByMobileAndType(@Param("mobile") String mobile, @Param("type") Byte type);
}