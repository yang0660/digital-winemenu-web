package com.myicellar.digitalmenu.service;

import com.myicellar.digitalmenu.dao.entity.UserAccount;
import com.myicellar.digitalmenu.dao.mapper.UserAccountMapperExt;
import com.myicellar.digitalmenu.enums.DeviceTypeEnum;
import com.myicellar.digitalmenu.enums.PassWordStatusEnum;
import com.myicellar.digitalmenu.enums.UserTypeEnum;
import com.myicellar.digitalmenu.shiro.ManageUserNamePasswordToken;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.PrincipalContext;
import com.myicellar.digitalmenu.utils.StringUtil;
import com.myicellar.digitalmenu.vo.request.UpdatePasswordReqVO;
import com.myicellar.digitalmenu.vo.response.LoginRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
@Slf4j
public class UserAccountService extends BaseService<Long, UserAccount, UserAccountMapperExt> {

    @Autowired
    private LoginRecordService loginRecordService;

    @Transactional
    public UserAccount queryByUserName(String userName, UserTypeEnum userTypeEnum) {
        return mapper.selectByUserNameAndType(userName, userTypeEnum.value);
    }

    @Transactional
    public UserAccount queryByUserName(String userName) {
        return mapper.selectByUserName(userName);
    }

    /**
     * 常规登录-shiro登录处理
     *
     * @param user
     * @param requestIp
     * @return
     */
    public ResultVO postLogin(UserAccount user, String password, String requestIp) {
        ResultVO result = null;
        Subject subject = SecurityUtils.getSubject();
        try {
            ManageUserNamePasswordToken usernamePasswordDeviceToken = new ManageUserNamePasswordToken(String.valueOf(user.getUserId()), password, DeviceTypeEnum.H5);
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
            log.error("用户不存在!", e);
            result = ResultVO.validError("用户不存在!");
        } catch (AuthenticationException e) {
            log.error("密码错误，请重试!", e);
            result = ResultVO.validError("密码错误，请重试!");
        } catch (Exception e) {
            log.error("网络异常，请稍后重试!", e);
            result = ResultVO.validError("网络异常，请稍后重试!");
        }

        //记录登录日志
        loginRecordService.saveLoginRecord(user, requestIp, result.getMessage());
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
}