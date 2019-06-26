package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.LoginRecord;
import com.myicellar.digitalmenu.dao.entity.UserAccount;
import com.myicellar.digitalmenu.dao.mapper.LoginRecordMapperExt;
import com.myicellar.digitalmenu.dao.mapper.UserRoleMapperExt;
import com.myicellar.digitalmenu.enums.LoginTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class LoginRecordService extends BaseService<Long, LoginRecord, LoginRecordMapperExt> {

    @Autowired
    private UserRoleMapperExt userRoleMapperExt;

    /**
     * 保存登录日志
     * @param userAccount
     * @param message
     */
    public void saveLoginRecord(UserAccount userAccount, String loginIp, String message){
        LoginRecord lr = new LoginRecord();
        Long id = snowflakeIdWorker.nextId();
        lr.setId(id);
        lr.setUserId(userAccount.getUserId());
        lr.setUserName(userAccount.getUserName());
        lr.setLoginIp(loginIp);
        lr.setLoginTime(new Date());
        lr.setType(LoginTypeEnum.LOGIN.value);

        //获取登录时的角色名称列表
        Set<String> roleNames = userRoleMapperExt.selectRoleNamesByUserId(userAccount.getUserId());
        int count = 0;
        StringBuffer roleNameStr = new StringBuffer("");
        for(String roleName : roleNames){
            if(count!=0){
                roleNameStr.append(",");
            }
            roleNameStr.append(roleName);
            count++;
        }
        lr.setRoleName(roleNameStr.toString());
        mapper.insertSelective(lr);

     }

    /**
     * 保存退出日志
     * @param userId
     * @param userName
     * @param loginIp
     */
    public void saveLogoutRecord(Long userId,String userName,String loginIp){
        LoginRecord lr = new LoginRecord();
        Long id = snowflakeIdWorker.nextId();
        lr.setId(id);
        lr.setUserId(userId);
        lr.setUserName(userName);
        lr.setLoginIp(loginIp);
        lr.setLoginTime(new Date());
        lr.setType(LoginTypeEnum.LOGIN.value);

        //获取登录时的角色名称列表
        Set<String> roleNames = userRoleMapperExt.selectRoleNamesByUserId(userId);
        int count = 0;
        StringBuffer roleNameStr = new StringBuffer("");
        for(String roleName : roleNames){
            if(count!=0){
                roleNameStr.append(",");
            }
            roleNameStr.append(roleName);
            count++;
        }
        lr.setRoleName(roleNameStr.toString());
        mapper.insertSelective(lr);
    }
}