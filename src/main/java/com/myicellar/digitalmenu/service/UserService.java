package com.myicellar.digitalmenu.service;

import com.alibaba.fastjson.JSONObject;
import com.myicellar.digitalmenu.dao.entity.*;
import com.myicellar.digitalmenu.dao.mapper.DepartmentMapperExt;
import com.myicellar.digitalmenu.dao.mapper.UserAccountMapperExt;
import com.myicellar.digitalmenu.dao.mapper.UserMapperExt;
import com.myicellar.digitalmenu.dao.mapper.UserRoleMapperExt;
import com.myicellar.digitalmenu.enums.DeleteStatusEnum;
import com.myicellar.digitalmenu.enums.UserSexEnum;
import com.myicellar.digitalmenu.enums.UserStatusEnum;
import com.myicellar.digitalmenu.enums.UserTypeEnum;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.PrincipalContext;
import com.myicellar.digitalmenu.utils.StringUtil;
import com.myicellar.digitalmenu.vo.request.UserPageReqVO;
import com.myicellar.digitalmenu.vo.request.UserReqVO;
import com.myicellar.digitalmenu.vo.request.UserUpdateReqVO;
import com.myicellar.digitalmenu.vo.response.PageResponseVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.UserListRespVO;
import com.myicellar.digitalmenu.vo.response.UserRespVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserService extends BaseService<Long, User, UserMapperExt> {

    @Autowired
    private DepartmentMapperExt departmentMapperExt;

    @Autowired
    private UserAccountMapperExt userAccountMapperExt;

    @Autowired
    private UserRoleMapperExt userRoleMapperExt;

    /**
     * 查询用户列表(分页，支持模糊匹配)
     * @return
     */
    public PageResponseVO<UserListRespVO> queryUserList(UserPageReqVO reqVO){
        PageResponseVO<UserListRespVO> page = selectPage(reqVO, mapper::selectCountByCondition, mapper::selectListByCondition);
        if(page!=null && !CollectionUtils.isEmpty(page.getItems())){
            page.getItems().forEach(respVO ->{
                Set<Role> roles = userRoleMapperExt.selectRolesByUserId(respVO.getUserId());
                Set<Long> roleIds = new HashSet<Long>();
                Set<String> roleNames = new HashSet<String>();
                if(!CollectionUtils.isEmpty(roles)) {
                    for (Role role : roles) {
                        roleIds.add(role.getRoleId());
                        roleNames.add(role.getRoleName());
                    }
                }
                respVO.setRoleIds(roleIds);
                respVO.setRoleNames(roleNames);
            });
        }

        return page;
    }

    /**
     * 查询用户详情
     * @return
     */
    public UserListRespVO queryUserInfo(UserPageReqVO reqVO){
        UserListRespVO respVO = null;
        reqVO.setPageNumber(1);
        reqVO.setPageSize(1);
        List<UserListRespVO> list = mapper.selectListByCondition(reqVO);
        if(list!=null && !CollectionUtils.isEmpty(list)){
            list.forEach(info ->{
                Set<Role> roles = userRoleMapperExt.selectRolesByUserId(info.getUserId());
                Set<Long> roleIds = new HashSet<Long>();
                Set<String> roleNames = new HashSet<String>();
                if(!CollectionUtils.isEmpty(roles)) {
                    for (Role role : roles) {
                        roleIds.add(role.getRoleId());
                        roleNames.add(role.getRoleName());
                    }
                }
                info.setRoleIds(roleIds);
                info.setRoleNames(roleNames);
            });
            respVO = list.get(0);
        }

        return respVO;
    }

    /**
     * 根据部门ID查询用户数量
     * @return
     */
    public int queryCountByDeptId(Long deptId){
        return mapper.queryCountByDeptId(deptId);
    }

    /**
     * 添加新用户
     * @param reqVO
     * @return
     */
    @Transactional
    public ResultVO addNew(UserReqVO reqVO){
        log.info("添加新用户，reqVO:"+ JSONObject.toJSONString(reqVO));
        StringBuffer substr = new StringBuffer("添加新用户，reqVO:")
                .append(JSONObject.toJSONString(reqVO));
        Long currentUserId = PrincipalContext.getCurrentUserId();
        //参数校验
        ResultVO result = checkNewParam(reqVO);
        if(result!=null){
            substr.append(" ").append(result.getMessage());
            log.error(substr.toString());
            return result;
        }

        //生成用户ID
        Long userId = snowflakeIdWorker.nextId();
        reqVO.setUserId(userId);
        reqVO.setCreateUser(currentUserId);
        reqVO.setUpdateUser(currentUserId);
        Date now = new Date();
        reqVO.setCreateTime(now);
        reqVO.setUpdateTime(now);

        //保存用户信息
        User user = ConvertUtils.convert(reqVO,User.class);
        user.setDeleteStatus(DeleteStatusEnum.NORMAL.value); //未删除
        int count = mapper.insertSelective(user);
        if(count==0){
            result = ResultVO.validError("添加失败!");
            return result;
        }
        //保存用户帐户信息
        UserAccount userAccount = ConvertUtils.convert(reqVO,UserAccount.class);
        userAccount.setUserType(UserTypeEnum.EMPLOYEE.value); //员工
        userAccount.setDeleteStatus(DeleteStatusEnum.NORMAL.value); //未删除
        saveUserAccount(userAccount);
        if(!CollectionUtils.isEmpty(reqVO.getRoleIds())){
            //批量保存用户角色权限
            batchSaveUserRole(reqVO.getUserId(),reqVO.getRoleIds(),currentUserId);
        }

        result = ResultVO.success("添加成功!");
        return result;
    }

    /**
     * 修改用户信息
     * @param reqVO
     * @return
     */
    @Transactional
    public ResultVO update(UserReqVO reqVO){
        log.info("修改用户信息，reqVO:"+ JSONObject.toJSONString(reqVO));
        StringBuffer substr = new StringBuffer("修改用户信息，reqVO:")
                .append(JSONObject.toJSONString(reqVO));
        Long currentUserId = PrincipalContext.getCurrentUserId();
        //参数校验
        ResultVO result = checkUpdateParam(reqVO);
        if(result!=null){
            substr.append(" ").append(result.getMessage());
            log.error(substr.toString());
            return result;
        }

        reqVO.setUpdateUser(currentUserId);
        Date now = new Date();
        reqVO.setUpdateTime(now);

        //修改用户信息
        User user = ConvertUtils.convert(reqVO,User.class);
        int count = mapper.updateByPrimaryKeySelective(user);
        if(count==0){
            result = ResultVO.validError("修改失败!");
            return result;
        }
        //保存用户帐户信息
        UserAccount userAccount = ConvertUtils.convert(reqVO,UserAccount.class);
        userAccount.setPassword(null);  //防止密码被误修改
        userAccountMapperExt.updateByPrimaryKeySelective(userAccount);
        if(!CollectionUtils.isEmpty(reqVO.getRoleIds())){
            //删除用户的历史角色权限
            userRoleMapperExt.deleteByUserId(reqVO.getUserId());
            //批量保存用户角色权限
            batchSaveUserRole(reqVO.getUserId(),reqVO.getRoleIds(),currentUserId);
        }

        result = ResultVO.success("修改成功!");
        return result;
    }

    /**
     * 修改个人信息
     * @param reqVO
     * @return
     */
    @Transactional
    public ResultVO updateUserInfo(UserUpdateReqVO reqVO){
        log.info("修改个人信息，reqVO:"+ JSONObject.toJSONString(reqVO));
        StringBuffer substr = new StringBuffer("修改个人信息，reqVO:")
                .append(JSONObject.toJSONString(reqVO));
        Long currentUserId = PrincipalContext.getCurrentUserId();
        //参数校验
        ResultVO result = checkUpdateInfoParam(reqVO,currentUserId);
        if(result!=null){
            substr.append(" ").append(result.getMessage());
            log.error(substr.toString());
            return result;
        }

        //修改用户信息
        User user = ConvertUtils.convert(reqVO,User.class);
        user.setUserId(currentUserId);
        Date now = new Date();
        user.setUpdateTime(now);
        int count = mapper.updateByPrimaryKeySelective(user);
        if(count==0){
            result = ResultVO.validError("修改失败!");
            return result;
        }
        //保存用户帐户信息
        UserAccount userAccount = ConvertUtils.convert(reqVO,UserAccount.class);
        userAccount.setUserId(currentUserId);
        userAccount.setPassword(null);  //防止密码被误修改
        userAccount.setUpdateTime(now);
        userAccountMapperExt.updateByPrimaryKeySelective(userAccount);
        result = ResultVO.success("修改成功!");
        return result;
    }

    /**
     * 修改用户状态
     * @param reqVO
     * @return
     */
    public ResultVO updateStatus(UserReqVO reqVO){
        Long currentUserId = PrincipalContext.getCurrentUserId();
        User user1 = mapper.selectByPrimaryKey(reqVO.getUserId());
        if(user1==null){
            return ResultVO.validError("用户不存在!");
        }
        //状态判断
        UserStatusEnum userStatusEnum = UserStatusEnum.enumOf(reqVO.getStatus());
        if(userStatusEnum==null){
            return ResultVO.validError("状态无效！");
        }

        User user = new User();
        user.setUserId(reqVO.getUserId());
        user.setStatus(reqVO.getStatus());
        user.setUpdateUser(currentUserId);
        Date now = new Date();
        user.setUpdateTime(now);
        //修改用户状态
        mapper.updateByPrimaryKeySelective(user);

        //修改用户帐户信息
        UserAccount userAccount = ConvertUtils.convert(reqVO,UserAccount.class);
        userAccount.setUserId(reqVO.getUserId());
        userAccount.setStatus(reqVO.getStatus());
        userAccount.setUpdateTime(now);
        userAccountMapperExt.updateByPrimaryKeySelective(userAccount);

        return ResultVO.success("修改成功!");
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    public ResultVO deleteById(Long userId){
        Long currentUserId = PrincipalContext.getCurrentUserId();
        User user1 = mapper.selectByPrimaryKey(userId);
        if(user1==null){
            return ResultVO.validError("用户不存在!");
        }

        User user = new User();
        user.setUserId(userId);
        user.setDeleteStatus(DeleteStatusEnum.DELETED.value);  //已删除
        user.setUpdateUser(currentUserId);
        Date now = new Date();
        user.setUpdateTime(now);
        //修改用户状态
        mapper.updateByPrimaryKeySelective(user);

        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(userId);
        userAccount.setDeleteStatus(DeleteStatusEnum.DELETED.value);  //已删除
        userAccount.setUpdateUser(currentUserId);
        userAccount.setUpdateTime(now);
        //修改用户状态
        userAccountMapperExt.updateByPrimaryKeySelective(userAccount);

        return ResultVO.success("修改成功!");
    }

    /**
     * 保存用户帐户信息
     * @param userAccount
     */
    public void saveUserAccount(UserAccount userAccount){
        String salt = StringUtil.genRandomStr(4); //加密因子
        userAccount.setSalt(salt);
        userAccount.setPassword(PrincipalContext.getMd5HashPwd(userAccount.getPassword(),salt));
        userAccountMapperExt.insertSelective(userAccount);
    }

    /**
     * 批量保存用户角色权限
     * @param userId
     * @param roleIds
     * @param currentUserId
     */
    public void batchSaveUserRole(Long userId,List<Long> roleIds,Long currentUserId){
        if(!CollectionUtils.isEmpty(roleIds)){
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setCreateUser(currentUserId);
            userRole.setUpdateUser(currentUserId);
            Date now = new Date();
            userRole.setCreateTime(now);
            userRole.setUpdateTime(now);

            for(Long roleId : roleIds){
                Long id = snowflakeIdWorker.nextId();
                userRole.setId(id);
                userRole.setRoleId(roleId);
                //保存用户角色权限
                userRoleMapperExt.insertSelective(userRole);
            }
        }
    }

    /**
     * 添加用户参数校验
     * @param reqVO
     * @return
     */
    public ResultVO checkNewParam(UserReqVO reqVO){
        ResultVO result = null;
        //用户名判空
        if(StringUtils.isEmpty(reqVO.getUserName())){
            return ResultVO.validError("用户名不能为空!");
        }
        //手机号码判空
        if(StringUtils.isEmpty(reqVO.getMobile())){
            return ResultVO.validError("手机号码不能为空!");
        }
        //密码判空
        if(StringUtils.isEmpty(reqVO.getPassword())){
            return ResultVO.validError("密码不能为空!");
        }
        //用户姓名判空
        if(StringUtils.isEmpty(reqVO.getRealName())){
            return ResultVO.validError("用户姓名不能为空!");
        }
        //工号判空
        if(StringUtils.isEmpty(reqVO.getUserCode())){
            return ResultVO.validError("工号不能为空!");
        }
        //用户名判重
        result = checkUserNameRepeat(reqVO.getUserName());
        if(result!=null){
            return result;
        }
        //手机号码判重
        result = checkMobileRepeat(reqVO.getMobile());
        if(result!=null){
            return result;
        }
        //工号判重
        result = checkUserCodeRepeat(reqVO.getUserCode());
        if(result!=null){
            return result;
        }
        //部门
        Department dept = departmentMapperExt.selectByPrimaryKey(reqVO.getDepartmentId());
        if(dept==null){
            return ResultVO.validError("部门无效！");
        }
        //性别判断
        UserSexEnum userSexEnum = UserSexEnum.enumOf(reqVO.getGender());
        if(userSexEnum==null){
            return ResultVO.validError("性别无效！");
        }
        //状态判断
        UserStatusEnum userStatusEnum = UserStatusEnum.enumOf(reqVO.getStatus());
        if(userStatusEnum==null){
            return ResultVO.validError("状态无效！");
        }
        UserAccount userAccount = userAccountMapperExt.selectByMobile(reqVO.getMobile());
        if(userAccount!=null) {
            return ResultVO.validError("手机号码为\"" + reqVO.getMobile() + "\"的用户已存在!");
        }

        return result;
    }

    /**
     * 修改用户参数校验
     * @param reqVO
     * @return
     */
    public ResultVO checkUpdateParam(UserReqVO reqVO){
        ResultVO result = null;
        if(reqVO.getUserId()==null || reqVO.getUserId()==0L){
            return ResultVO.validError("用户ID无效!");
        }
        User user = mapper.selectByPrimaryKey(reqVO.getUserId());
        if(user==null){
            return ResultVO.validError("用户不存在!");
        }
        //用户姓名判空
        if(StringUtils.isEmpty(reqVO.getRealName())){
            return ResultVO.validError("用户姓名不能为空!");
        }
        //工号判空
        if(StringUtils.isEmpty(reqVO.getUserCode())){
            return ResultVO.validError("工号不能为空!");
        }
        //工号判重
        User user1 = mapper.selectByUserCode(reqVO.getUserCode());
        if(user1!=null && !user1.getUserId().equals(reqVO.getUserId())){
            return ResultVO.validError("工号已存在!");
        }
        //部门
        Department dept = departmentMapperExt.selectByPrimaryKey(reqVO.getDepartmentId());
        if(dept==null){
            return ResultVO.validError("部门无效！");
        }
        //性别判断
        UserSexEnum userSexEnum = UserSexEnum.enumOf(reqVO.getGender());
        if(userSexEnum==null){
            return ResultVO.validError("性别无效！");
        }
        //状态判断
        UserStatusEnum userStatusEnum = UserStatusEnum.enumOf(reqVO.getStatus());
        if(userStatusEnum==null){
            return ResultVO.validError("状态无效！");
        }

        UserAccount mobileUa = userAccountMapperExt.selectByMobile(reqVO.getMobile());
        if(mobileUa!=null && !mobileUa.getUserId().equals(reqVO.getUserId())){
            return ResultVO.validError("手机号已被使用!");
        }

        return result;
    }

    /**
     * 修改个人信息参数校验
     * @param reqVO
     * @return
     */
    public ResultVO checkUpdateInfoParam(UserUpdateReqVO reqVO,Long currentUserId){
        ResultVO result = null;
        //用户名判空
        if(StringUtils.isEmpty(reqVO.getUserName())){
            return ResultVO.validError("用户名不能为空!");
        }
        //手机号码判空
        if(StringUtils.isEmpty(reqVO.getMobile())){
            return ResultVO.validError("手机号码不能为空!");
        }
        //性别判断
        UserSexEnum userSexEnum = UserSexEnum.enumOf(reqVO.getGender());
        if(userSexEnum==null){
            return ResultVO.validError("性别无效！");
        }
        UserAccount userNameUa = userAccountMapperExt.selectByUserName(reqVO.getUserName());
        if(userNameUa!=null && !userNameUa.getUserId().equals(currentUserId)){
            return ResultVO.validError("用户名已存在!");
        }
        UserAccount mobileUa = userAccountMapperExt.selectByMobile(reqVO.getMobile());
        if(mobileUa!=null && !mobileUa.getUserId().equals(currentUserId)){
            return ResultVO.validError("手机号已被使用!");
        }

        return result;
    }

    /**
     * 工号防重检测
     * @param userCode
     * @return
     */
    public ResultVO checkUserCodeRepeat(String userCode){
        ResultVO result = null;
        User user = mapper.selectByUserCode(userCode);
        if(user!=null){
            return ResultVO.validError("工号已存在!");
        }
        return result;
    }

    /**
     * 用户防重检测
     * @param userName
     * @return
     */
    public ResultVO checkUserNameRepeat(String userName){
        ResultVO result = null;
        UserAccount userAccount = userAccountMapperExt.selectByUserName(userName);
        if(userAccount!=null){
            return ResultVO.validError("用户名已存在!");
        }
        return result;
    }

    /**
     * 手机号码防重检测
     * @param mobile
     * @return
     */
    public ResultVO checkMobileRepeat(String mobile){
        ResultVO result = null;
        UserAccount userAccount = userAccountMapperExt.selectByMobile(mobile);
        if(userAccount!=null){
            return ResultVO.validError("手机号码已存在!");
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<UserRespVO> selectByRoleId(Long roleId) {
        return mapper.selectByRoleId(roleId);
    }
}