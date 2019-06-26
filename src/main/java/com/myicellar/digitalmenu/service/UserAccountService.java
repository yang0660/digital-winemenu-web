package com.myicellar.digitalmenu.service;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.myicellar.digitalmenu.dao.entity.SmsRecord;
import com.myicellar.digitalmenu.dao.entity.UserAccount;
import com.myicellar.digitalmenu.dao.mapper.SmsRecordMapperExt;
import com.myicellar.digitalmenu.dao.mapper.UserAccountMapperExt;
import com.myicellar.digitalmenu.enums.*;
import com.myicellar.digitalmenu.shiro.AppUserNamePasswordToken;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.PrincipalContext;
import com.myicellar.digitalmenu.utils.StringUtil;
import com.myicellar.digitalmenu.vo.request.*;
import com.myicellar.digitalmenu.vo.request.app.UserRegisterReqVO;
import com.myicellar.digitalmenu.vo.response.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserAccountService extends BaseService<Long, UserAccount, UserAccountMapperExt> {

    @Autowired
    private SmsRecordMapperExt smsRecordMapperExt;

    @Autowired
    private LoginRecordService loginRecordService;

    @Autowired
    private ALiYunSmsService aLiYunSmsService;

    @Autowired
    private AliyunPushService aliyunPushService;

    @Transactional(readOnly = true)
    public UserAccount selectByMobile(String mobile) {
        return mapper.selectByMobile(mobile);
    }

    @Transactional
    public int save(UserAccount userAccount) {
        userAccount.setUserId(snowflakeIdWorker.nextId());
        return insertSelective(userAccount);
    }

    @Transactional
    public int updateAuditStatus(UserAccountReqVO userAccountReqVO) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(userAccountReqVO.getUserId());
        userAccount.setMaterialStatus(userAccountReqVO.getMaterialStatus());
        userAccount.setUpdateTime(userAccountReqVO.getCreateTime());
        userAccount.setUpdateUser(userAccountReqVO.getCreateUser());
        return updateByPrimaryKeySelective(userAccount);
    }

    @Transactional
    public UserAccount queryByUserName(String userName, UserTypeEnum userTypeEnum){
        return mapper.selectByUserNameAndType(userName,userTypeEnum.value);
    }

    @Transactional
    public UserAccount queryByUserName(String userName){
        return mapper.selectByUserName(userName);
    }

    @Transactional(readOnly = true)
    public List<UserAccount> selectByRoleId(Long roleId) {
        return mapper.selectByRoleId(roleId);
    }

    @Transactional(readOnly = true)
    public List<UserAccount> selectByRoleIdAndDepartmentId(Long roleId, Long departmentId) {
        return mapper.selectByRoleIdAndDepartmentId(roleId, departmentId);
    }

    @Transactional
    public UserListRespVO selectUserInfoById(Long userId) {
        return mapper.selectUserInfoById(userId);
    }

    /**
     * APP用户注册
     * @param reqVO
     */
    @Transactional
    public ResultVO register(UserRegisterReqVO reqVO){
        log.info("APP用户注册，reqVO:"+ JSONObject.toJSONString(reqVO));
        /*if(StringUtils.isEmpty(reqVO.getUserName())){
            return ResultVO.validError("用户名不能为空!");
        }*/
        if(StringUtils.isEmpty(reqVO.getMobile())){
            return ResultVO.validError("手机号码不能为空!");
        }
        if(StringUtils.isEmpty(reqVO.getPassword())){
            return ResultVO.validError("密码不能为空!");
        }
        if(StringUtils.isEmpty(reqVO.getSmsCode())){
            return ResultVO.validError("验证码不能为空!");
        }
        UserAccount mobileUa = mapper.selectByMobile(reqVO.getMobile());
        if(mobileUa==null){
            return ResultVO.validError("手机号不存在，请联系管理员!");
        }else{
            //员工可直接登录APP，无需注册
            if(UserTypeEnum.EMPLOYEE.value.equals(mobileUa.getUserType())){
                return ResultVO.validError("内部员工无需注册!");
            }
            if(!StringUtils.isEmpty(mobileUa.getPassword())){
                return ResultVO.validError("手机号已被注册!");
            }
            if(UserStatusEnum.CLOSED.value.equals(mobileUa.getStatus())){
                return ResultVO.validError("手机号已被停用，请联系管理员!");
            }
            /*UserAccount userNameUa = mapper.selectByUserName(reqVO.getUserName());
            if(userNameUa!=null && !userNameUa.getUserId().equals(mobileUa.getUserId())){
                return ResultVO.validError("用户名已被注册!");
            }*/
        }

        ResultVO result = checkSmsCode(reqVO.getMobile(),reqVO.getSmsCode(), SmsCodeTypeEnum.REGISTER);
        if(result!=null){
            return result;
        }

        //保存帐号信息
        mobileUa.setUserName(reqVO.getUserName()); //设置新的登录用户名
        String salt = StringUtil.genRandomStr(4); //加密因子
        mobileUa.setSalt(salt);
        mobileUa.setPassword(PrincipalContext.getMd5HashPwd(reqVO.getPassword(),salt));
        mobileUa.setUpdateTime(new Date());
        mapper.updateByPrimaryKeySelective(mobileUa);

        return ResultVO.success("注册成功!");
    }

    /**
     * 忘记密码-重置密码
     * @param reqVO
     * @return
     */
    public ResultVO updateManagePasswordForget(UpdatePasswordForgetReqVO reqVO){
        log.info("忘记密码-重置密码，reqVO:"+ JSONObject.toJSONString(reqVO));
        if(StringUtils.isEmpty(reqVO.getMobile())){
            return ResultVO.validError("手机号码不能为空!");
        }
        if(StringUtils.isEmpty(reqVO.getPassword())){
            return ResultVO.validError("密码不能为空!");
        }
        if(StringUtils.isEmpty(reqVO.getSmsCode())){
            return ResultVO.validError("验证码不能为空!");
        }
        UserAccount mobileUa = mapper.selectByMobile(reqVO.getMobile());
        if(mobileUa==null){
            return ResultVO.validError("手机号不存在，请联系管理员!");
        }
        if(!UserTypeEnum.EMPLOYEE.value.equals(mobileUa.getUserType())){
            return ResultVO.validError("用户不存在，请联系管理员!");
        }
        ResultVO result = checkSmsCode(reqVO.getMobile(),reqVO.getSmsCode(),SmsCodeTypeEnum.RESET_PASSWORD);
        if(result!=null){
            return result;
        }

        //保存帐号信息
        String salt = StringUtil.genRandomStr(4); //加密因子
        mobileUa.setSalt(salt);
        mobileUa.setPassword(PrincipalContext.getMd5HashPwd(reqVO.getPassword(),salt));
        mobileUa.setUpdateTime(new Date());
        mapper.updateByPrimaryKeySelective(mobileUa);

        return ResultVO.success("处理成功!");
    }

    /**
     * 忘记密码-重置密码
     * @param reqVO
     * @return
     */
    public ResultVO updateAppPasswordForget(UpdatePasswordForgetReqVO reqVO){
        log.info("忘记密码-重置密码，reqVO:"+ JSONObject.toJSONString(reqVO));
        if(StringUtils.isEmpty(reqVO.getMobile())){
            return ResultVO.validError("手机号码不能为空!");
        }
        if(StringUtils.isEmpty(reqVO.getPassword())){
            return ResultVO.validError("密码不能为空!");
        }
        if(StringUtils.isEmpty(reqVO.getSmsCode())){
            return ResultVO.validError("验证码不能为空!");
        }
        UserAccount mobileUa = mapper.selectByMobile(reqVO.getMobile());
        if(mobileUa==null){
            return ResultVO.validError("手机号不存在，请联系管理员!");
        }
        ResultVO result = checkSmsCode(reqVO.getMobile(),reqVO.getSmsCode(),SmsCodeTypeEnum.RESET_PASSWORD);
        if(result!=null){
            return result;
        }

        //保存帐号信息
        String salt = StringUtil.genRandomStr(4); //加密因子
        mobileUa.setSalt(salt);
        mobileUa.setPassword(PrincipalContext.getMd5HashPwd(reqVO.getPassword(),salt));
        mobileUa.setUpdateTime(new Date());
        mapper.updateByPrimaryKeySelective(mobileUa);

        return ResultVO.success("处理成功!");
    }

    /**
     * 常规登录-shiro登录处理
     * @param user
     * @param requestIp
     * @return
     */
    public ResultVO postLogin(UserAccount user,String password,String requestIp){
        ResultVO result = null;
        Subject subject = SecurityUtils.getSubject();
        try {
            AppUserNamePasswordToken usernamePasswordDeviceToken = new AppUserNamePasswordToken(String.valueOf(user.getUserId()), password, DeviceTypeEnum.H5);
            subject.login(usernamePasswordDeviceToken);

            //登录成功
            LoginRespVO loginRespVO = ConvertUtils.convert(user, LoginRespVO.class);
            //登录token
            String sessionId = (String) subject.getSession().getId();
            loginRespVO.setToken(sessionId);
            Session session = SecurityUtils.getSubject().getSession(false);
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("passwordStatus", PassWordStatusEnum.EFFECTIVE.name());
            result = ResultVO.success("登录成功");
            result.setData(loginRespVO);
        } catch (UnknownAccountException e) {
            log.error("用户不存在!",e);
            result = ResultVO.validError("用户不存在!");
        } catch (AuthenticationException e) {
            log.error("密码错误，请重试!",e);
            result = ResultVO.validError("密码错误，请重试!");
        } catch (Exception e) {
            log.error("网络异常，请稍后重试!",e);
            result = ResultVO.validError("网络异常，请稍后重试!");
        }

        //记录登录日志
        loginRecordService.saveLoginRecord(user,requestIp,result.getMessage());
        return result;
    }

    /**
     * 修改密码
     * @param reqVO
     */
    @Transactional
    public ResultVO updatePassword(UpdatePasswordReqVO reqVO) {
        Long currentUserId = PrincipalContext.getCurrentUserId();  //当前登录用户
        if (StringUtils.isEmpty(reqVO.getOldPassword())) {
            return ResultVO.validError("原密码不能为空!");
        }
        if (StringUtils.isEmpty(reqVO.getPassword())) {
            return ResultVO.validError("新密码不能为空!");
        }
        UserAccount ua = mapper.selectByPrimaryKey(currentUserId);
        //校验原密码
        if (!ua.getPassword().equals(PrincipalContext.getMd5HashPwd(reqVO.getOldPassword(), ua.getSalt()))) {
            return ResultVO.validError("原密码有误!");
        }
        //保存新密码
        String salt = StringUtil.genRandomStr(4); //加密因子
        ua.setSalt(salt);
        ua.setPassword(PrincipalContext.getMd5HashPwd(reqVO.getPassword(),salt));
        ua.setUpdateTime(new Date());
        mapper.updateByPrimaryKeySelective(ua);

        return ResultVO.success("修改密码成功!");
    }

    /**
     * 修改个人资料
     * @param reqVO
     */
    @Transactional
    public ResultVO update(UserReqVO reqVO){
        Long currentUserId = PrincipalContext.getCurrentUserId();  //当前登录用户
        if(StringUtils.isEmpty(reqVO.getRealName())){
            return ResultVO.validError("用户姓名不能为空!");
        }
        if(StringUtils.isEmpty(reqVO.getMobile())){
            return ResultVO.validError("手机号不能为空!");
        }
        UserSexEnum userSexEnum = UserSexEnum.enumOf(reqVO.getGender());
        if(userSexEnum==null){
            return ResultVO.validError("性别数据有误!");
        }
        //手机号码校验
        UserAccount mobileUa = mapper.selectByMobile(reqVO.getMobile());
        if(mobileUa!=null && !mobileUa.getUserId().equals(currentUserId)){
            return ResultVO.validError("手机号已被使用!");
        }
        //修改帐号信息
        UserAccount ua = mapper.selectByPrimaryKey(currentUserId);
        ua.setMobile(reqVO.getMobile());
        ua.setRealName(reqVO.getRealName());
        ua.setGender(reqVO.getGender());
        Date now = new Date();
        ua.setUpdateTime(now);
        ua.setUpdateUser(currentUserId);
        mapper.updateByPrimaryKeySelective(ua);

        return ResultVO.success("","更新成功!");
    }

    public UserAccount queryByWxOpenId(String wxOpenId){
        return mapper.selectByWxOpenId(wxOpenId);
    }

    /**
     * 发送短信验证码
     * @param reqVO
     * @return
     */
    public ResultVO sendCodeSms(SendSmsCodeReqVO reqVO){
        ResultVO result = null;
        if(StringUtils.isEmpty(reqVO.getMobile())){
            return ResultVO.validError("手机号码不能为空!");
        }
        if(StringUtils.isEmpty(reqVO.getType())){
            return ResultVO.validError("验证码类型不能为空!");
        }
        SmsCodeTypeEnum typeEnum = SmsCodeTypeEnum.enumOf(reqVO.getType());
        if(typeEnum==null){
            return ResultVO.validError("验证码类型无效!");
        }

        UserAccount ua = mapper.selectByMobile(reqVO.getMobile());
        if(ua==null){
            if(SmsCodeTypeEnum.RESET_PASSWORD.value.equals(reqVO.getType())) {
                return ResultVO.validError("手机号不存在，请联系管理员!");
            }
        }else{
            if(!StringUtils.isEmpty(ua.getPassword()) && SmsCodeTypeEnum.REGISTER.value.equals(reqVO.getType())){
                return ResultVO.validError("该手机号已被注册!");
            }
        }

        //每日发送次数校验
        List<SmsRecord> smsRecords = smsRecordMapperExt.selectTodayRecordsByMobileAndType(reqVO.getMobile(),reqVO.getType());
        if(smsRecords!=null && smsRecords.size()>5){
            return ResultVO.validError("当前验证码发送次数超限(5次)，请明天再试!");
        }
        //生成随机验证码
        String smsCode = StringUtil.createCode();
        //发送验证码短信
        Map<String,String> jsonMap = new HashMap<String,String>();
        jsonMap.put("code",smsCode);
        SendSmsResponse response = aLiYunSmsService.sendMsg(reqVO.getMobile(),
                JSONObject.toJSONString(jsonMap));
        if(response==null || !"OK".equals(response.getCode())){
            return ResultVO.validError("发送失败，请重试!");
        }

        SmsRecord smsRecord = new SmsRecord();
        Long id = snowflakeIdWorker.nextId();
        smsRecord.setId(id);
        smsRecord.setMobileNo(reqVO.getMobile());
        smsRecord.setBusinessType(reqVO.getType());
        smsRecord.setAuthCode(smsCode);
        Date now = new Date();
        smsRecord.setCreateTime(now);
        //保存验证码短信发送记录
        smsRecordMapperExt.insertSelective(smsRecord);

        return ResultVO.success("发送成功!");
    }

    /**
     * 校验短信验证码
     * @param mobile
     * @param smsCode
     * @param typeEnum
     * @return
     */
    public ResultVO checkSmsCode(String mobile,String smsCode,SmsCodeTypeEnum typeEnum){
        ResultVO result = null;
        if(StringUtils.isEmpty(mobile)){
            return ResultVO.validError("手机号码不能为空!");
        }
        if(StringUtils.isEmpty(smsCode)){
            return ResultVO.validError("验证码不能为空!");
        }
        if(typeEnum==null){
            return ResultVO.validError("验证码类型无效!");
        }
        //查询验证码短信发送记录
        SmsRecord smsRecord = smsRecordMapperExt.selectLastByMobileAndType(mobile,typeEnum.value);
        if(smsRecord==null){
            return ResultVO.validError("请先发送验证码!");
        }
        Date sendTime = smsRecord.getCreateTime();
        Date now = new Date();
        //5分钟有效
        if(now.getTime()-sendTime.getTime()> 5*60*1000){
            return ResultVO.validError("验证码已过期，请重新发送!");
        }
        if(!smsCode.equals(smsRecord.getAuthCode())){
            return ResultVO.validError("验证码错误，请重试!");
        }

        return result;
    }

    /**
     * 发送模板短信通知
     * @param userId
     * @param jsonMap
     * @param templateCode
     * @return
     */
    public ResultVO sendNoticeSms(Long userId,Map<String,String> jsonMap, String templateCode){
        try {
            log.info("发送短信通知,userId:{},jsonMap:{},templateCode:{}",userId,jsonMap,templateCode);
            UserAccount ua = mapper.selectByPrimaryKey(userId);
            //发送模板短信
            SendSmsResponse response = aLiYunSmsService.sendMsg(ua.getMobile(),
                    JSONObject.toJSONString(jsonMap), templateCode);
            if (response == null || !"OK".equals(response.getCode())) {
                return ResultVO.validError("发送失败，请重试!");
            }
        }catch(Exception e){
            log.error("发送短信通知异常:",e);
            return ResultVO.validError("发送短信通知异常");
        }

        return ResultVO.success("发送成功!");
    }

    public List<UserAccount> selectListAll() {
        return mapper.selectListAll();
    }

    @Transactional(readOnly = true)
    public List<UserAccount> selectByRealName(String realName) {
        return mapper.selectByRealName(realName);
    }

    @Transactional(readOnly = true)
    public List<UserAccountSimpleRespVO> selectSimpleByUserIds(String userIds) {
        return selectSimpleByUserIds(str2List(userIds));
    }

    @Transactional(readOnly = true)
    public List<UserAccountSimpleRespVO> selectSimpleByUserIds(List<Long> userIds) {
        UserAccountReqVO reqVO = new UserAccountReqVO();
        reqVO.setUserIdList(userIds);
        return ConvertUtils.convert(selectListMaxSize(reqVO), UserAccountSimpleRespVO.class);
    }

    @Transactional(readOnly = true)
    public String selectRealNames(String userIds) {
        return str2List(userIds)
                .stream()
                .map(userId -> {
                    UserAccount userAccount = selectByPrimaryKey(userId);
                    if(userAccount != null) {
                        return userAccount.getRealName();
                    }
                    return null;
                }).filter(org.apache.commons.lang3.StringUtils::isNotBlank)
                .collect(Collectors.joining(","));
    }

    private List<Long> str2List(String userIds) {
        if(userIds == null || userIds.isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(userIds.split(","))
                .map(Long::parseLong).collect(Collectors.toList());
    }
    public PageResponseVO<UserListRespVO> selectUserInfo(UserAccountReqVO reqVO){

        PageResponseVO<UserListRespVO> responseVO = new PageResponseVO<UserListRespVO>();

        int count=mapper.countUserInfo(reqVO);
        if(count <1){
            return responseVO;
        }
        responseVO.setTotalCount(count);
        responseVO.setItems(mapper.selectUserInfo(reqVO));

        return responseVO;
    }

    /**
     * 删除userAccount记录（逻辑删除）
     * @param userId
     * @return
     */
    public int deleteByUserId(Long userId){
        Long currentUserId = PrincipalContext.getCurrentUserId();
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(userId);
        userAccount.setDeleteStatus(DeleteStatusEnum.DELETED.value);  //已删除
        userAccount.setUpdateUser(currentUserId);
        userAccount.setUpdateTime(new Date());
        //修改用户状态
        return mapper.updateByPrimaryKeySelective(userAccount);
    }

    public void pushToUser(Long userId,String msgTitle,String msgContent,Map<String,Object> extMap,String url){
        try {
            UserAccount userAccount = mapper.selectByPrimaryKey(userId);
            //阿里云推送
            Map<String, Object> extParams = new HashMap<String, Object>();
            if (!StringUtils.isEmpty(url)) {
                StringBuffer urlSubStr = new StringBuffer(url);
                if (!CollectionUtils.isEmpty(extMap)) {
                    Iterator<String> iterator = extMap.keySet().iterator();
                    urlSubStr.append("?");
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        urlSubStr.append(key).append("=").append(extMap.get(key));
                        if (iterator.hasNext()) {
                            urlSubStr.append("&");
                        }
                    }
                }

                extParams.put("url", urlSubStr.toString());
            }
            aliyunPushService.pushMessage(userAccount.getDeviceId(), userAccount.getDeviceType(), msgTitle, msgContent, extParams);
        }catch (Exception e){
            log.error("推送失败！",e);
        }
    }

    public ResultVO uploadPushInfo(UploadPushInfoReqVO reqVO){
        ResultVO resultVO = ResultVO.success("上传成功！");
        if(StringUtils.isEmpty(reqVO.getDeviceId()) || StringUtils.isEmpty(reqVO.getDeviceId())){
            return ResultVO.validError("参数有误！");
        }

        UserAccount userAccount = ConvertUtils.convert(reqVO, UserAccount.class);
        Integer count = mapper.updateByPrimaryKeySelective(userAccount);
        if(count==0){
            log.error("上传推送基础数据失败！reqVO:{}",JSONObject.toJSONString(reqVO));
            resultVO = ResultVO.success("上传失败！");
        }

        return resultVO;
    }

    public Map<Long,UserAccount> selectUserMapByIds(Set<Long> userIds){
        return mapper.selectUserMapByIds(userIds);
    }
}