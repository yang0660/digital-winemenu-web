package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.OperationRecord;
import com.myicellar.digitalmenu.dao.entity.UserAccount;
import com.myicellar.digitalmenu.dao.mapper.OperationRecordMapperExt;
import com.myicellar.digitalmenu.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationRecordService extends BaseService<Long, OperationRecord, OperationRecordMapperExt> {
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private UserAccountService userAccountService;
    public int saveOperationRecord(OperationRecord record){
         record.setId(snowflakeIdWorker.nextId());
         UserAccount userAccount = userAccountService.selectByPrimaryKey(record.getUserId());
         if(userAccount !=null){
            record.setUserName(userAccount.getRealName());
         }
         return  insertSelective(record);
     }
}