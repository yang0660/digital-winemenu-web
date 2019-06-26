package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.Menu;
import com.myicellar.digitalmenu.dao.entity.RolePermission;
import com.myicellar.digitalmenu.dao.mapper.MenuMapperExt;
import com.myicellar.digitalmenu.dao.mapper.RolePermissionMapperExt;
import com.myicellar.digitalmenu.utils.PrincipalContext;
import com.myicellar.digitalmenu.vo.request.RolePermissionSaveReqVO;
import com.myicellar.digitalmenu.vo.response.GrantPermissionRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.RoleGrantPermissionRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class RolePermissionService extends BaseService<Long, RolePermission, RolePermissionMapperExt> {

    @Autowired
    private MenuMapperExt menuMapperExt;

    /**
     * 查询角色授权情况
     * @return
     */
    public List<RoleGrantPermissionRespVO> queryPermissionsByRoleId(Long roleId){
        List<RoleGrantPermissionRespVO> resultList = new ArrayList<RoleGrantPermissionRespVO>();
        //查询菜单列表
        List<Menu> menuList = menuMapperExt.queryMenuList();
        if(!CollectionUtils.isEmpty(menuList)){
            for(Menu menu : menuList){
                RoleGrantPermissionRespVO respVO = new RoleGrantPermissionRespVO();
                //查询资源权限数据
                List<GrantPermissionRespVO> grantList = mapper.selectPermListByMenuRoleId(menu.getMenuId(),roleId);
                if(!CollectionUtils.isEmpty(grantList)) {
                    respVO.setGrantList(grantList);
                    respVO.setMenuId(menu.getMenuId());
                    respVO.setMenuName(menu.getMenuName());
                    resultList.add(respVO);
                }
            }
        }

        return resultList;
    }

    /**
     * 保存角色授权数据
     * @param reqVO
     * @return
     */
    public ResultVO savePermissions(RolePermissionSaveReqVO reqVO){
        Long roleId = reqVO.getRoleId();
        Long currentUserId = PrincipalContext.getCurrentUserId();
        if(roleId==null || roleId==0L){
            return ResultVO.validError("角色ID不能为空!");
        }

        List<Long> grantedList = reqVO.getGrantedList();  //已授权资源集合
        List<Long> unGrantedList = reqVO.getUnGrantedList(); //未授权资源集合
        Set<Long> oldPermIds = mapper.selectPermListByRoleId(roleId);
        if(CollectionUtils.isEmpty(oldPermIds)){
            //批量保存角色资源权限
            batchSaveRolePermission(roleId,grantedList,currentUserId);
        }else {
            for (Long permId : grantedList) {
                //历史权限不包含新的授权资源，则新增
                if (!oldPermIds.contains(permId)) {
                    RolePermission rp = new RolePermission();
                    rp.setId(snowflakeIdWorker.nextId());
                    rp.setRoleId(roleId);
                    rp.setPermissionId(permId);
                    rp.setCreateUser(currentUserId);
                    rp.setUpdateUser(currentUserId);
                    Date now = new Date();
                    rp.setCreateTime(now);
                    rp.setUpdateTime(now);
                    //插入角色权限
                    mapper.insertSelective(rp);
                }
            }

            for(Long permId : unGrantedList){
                //历史权限包含新的未授权资源，则删除
                if(oldPermIds.contains(permId)){
                    //插入角色权限
                    mapper.deleteByRolePermId(roleId,permId);
                }
            }
        }

        return ResultVO.success("保存成功!");
    }

    /**
     * 批量保存角色资源权限
     * @param roleId
     * @param grantedList
     * @param currentUserId
     * @return
     */
    public int batchSaveRolePermission(Long roleId,List<Long> grantedList,Long currentUserId){
        int count = 0;
        if(!CollectionUtils.isEmpty(grantedList)) {
            for (Long permId : grantedList) {
                //新增
                RolePermission rp = new RolePermission();
                rp.setId(snowflakeIdWorker.nextId());
                rp.setRoleId(roleId);
                rp.setPermissionId(permId);
                rp.setCreateUser(currentUserId);
                rp.setUpdateUser(currentUserId);
                Date now = new Date();
                rp.setCreateTime(now);
                rp.setUpdateTime(now);
                //插入角色权限
                mapper.insertSelective(rp);
                count++;
            }
        }

        return count;
    }

}