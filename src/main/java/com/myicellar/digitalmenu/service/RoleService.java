package com.myicellar.digitalmenu.service;

import com.alibaba.fastjson.JSONObject;
import com.myicellar.digitalmenu.dao.entity.Role;
import com.myicellar.digitalmenu.dao.mapper.RoleMapperExt;
import com.myicellar.digitalmenu.utils.PrincipalContext;
import com.myicellar.digitalmenu.vo.request.RolePageReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
@Slf4j
public class RoleService extends BaseService<Long, Role, RoleMapperExt> {

    /**
     * 查询角色列表(支持根据名称模糊匹配)
     * @return
     */
    public PageResponseVO<Role> queryRoleList(RolePageReqVO reqVO){
        return selectPage(reqVO, mapper::selectCount, mapper::selectList);
    }

    /**
     * 添加新角色
     * @param role
     * @return
     */
    @Transactional
    public ResultVO addNew(Role role){
        log.info("添加新角色，role:"+ JSONObject.toJSONString(role));
        StringBuffer substr = new StringBuffer("添加新角色，role:")
                .append(JSONObject.toJSONString(role));
        Long currentUserId = PrincipalContext.getCurrentUserId();
        //参数校验
        ResultVO result = checkNewParam(role);
        if(result!=null){
            substr.append(" ").append(result.getMessage());
            log.error(substr.toString());
            return result;
        }

        Long roleId = snowflakeIdWorker.nextId();
        role.setRoleId(roleId);
        role.setCreateUser(currentUserId);
        role.setUpdateUser(currentUserId);
        Date now = new Date();
        role.setCreateTime(now);
        role.setUpdateTime(now);
        int count = mapper.insertSelective(role);
        if(count==0){
            result = ResultVO.validError("添加失败!");
            return result;
        }

        result = ResultVO.success("添加成功!");
        return result;
    }

    /**
     * 添加角色参数判断
     * @param role
     * @return
     */
    public ResultVO checkNewParam(Role role){
        ResultVO result = null;
        //角色名称判空
        result = checkRoleNameEmpty(role.getRoleName());
        if(result!=null){
            return result;
        }
        //角色名称判重
        result = checkRoleNameRepeat(role.getRoleName());
        if(result!=null){
            return result;
        }

        return result;
    }

    /**
     * 角色名称非空检测
     * @param roleName
     * @return
     */
    public ResultVO checkRoleNameEmpty(String roleName){
        ResultVO result = null;
        if(StringUtils.isEmpty(roleName)){
            result = ResultVO.validError("角色名称不能为空!");
            return result;
        }
        return result;
    }
    /**
     * 角色名称防重检测
     * @param roleName
     * @return
     */
    public ResultVO checkRoleNameRepeat(String roleName){
        ResultVO result = null;
        Role dept = mapper.selectByRoleName(roleName);
        if(dept!=null){
            result = ResultVO.validError("角色名称已存在!");
            return result;
        }
        return result;
    }

}