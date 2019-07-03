package com.myicellar.digitalmenu.controller.manage;

import com.myicellar.digitalmenu.dao.entity.UserAccount;
import com.myicellar.digitalmenu.enums.DeviceTypeEnum;
import com.myicellar.digitalmenu.enums.PassWordStatusEnum;
import com.myicellar.digitalmenu.enums.UserTypeEnum;
import com.myicellar.digitalmenu.helper.ShiroHelper;
import com.myicellar.digitalmenu.service.LoginRecordService;
import com.myicellar.digitalmenu.service.UserAccountService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.shiro.ManageUserNamePasswordToken;
import com.myicellar.digitalmenu.utils.ConvertUtils;
import com.myicellar.digitalmenu.utils.PrincipalContext;
import com.myicellar.digitalmenu.utils.RequestUtil;
import com.myicellar.digitalmenu.vo.request.ManageLoginReqVO;
import com.myicellar.digitalmenu.vo.response.LoginRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@RequestMapping("/manage")
@Api(tags = "后台登录", description = "/manage")
public class ManageLoginController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private LoginRecordService loginRecordService;

    /**
     * login
     *
     * @param reqVO
     * @return
     * @since
     */
    @PostMapping(value = "/login")
    @AuthIgnore
    public ResultVO<LoginRespVO> login(@RequestBody ManageLoginReqVO reqVO, HttpServletRequest request, HttpServletResponse response) {
        String requestIp = RequestUtil.getUserRealIP(request); //请求的真实IP
        // 参数校验
        UserAccount user = userAccountService.queryByUserName(reqVO.getUserName(), UserTypeEnum.EMPLOYEE);
        ResultVO result = PrincipalContext.checkManageUser(user);

        if(result==null) {
            Subject subject = SecurityUtils.getSubject();
            try {
                ManageUserNamePasswordToken usernamePasswordDeviceToken = new ManageUserNamePasswordToken(String.valueOf(user.getUserId()), reqVO.getPassword(), DeviceTypeEnum.H5);
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
                log.error("用户名或密码错误!",e);
                result = ResultVO.validError("用户名或密码错误!");
            } catch (Exception e) {
                log.error("网络异常，请稍后重试!",e);
                result = ResultVO.validError("网络异常，请稍后重试!");
            }

            //记录登录日志
            loginRecordService.saveLoginRecord(user,requestIp,result.getMessage());
        }

        return result;
    }

    @PostMapping(value = "/logout")
    public ResultVO logout(HttpServletRequest request, HttpServletResponse response) {
        String requestIp = RequestUtil.getUserRealIP(request);
        log.info("进入loginout方法");
        Long userId = PrincipalContext.getCurrentUserId();
        String userName = PrincipalContext.getCurrentUserName();
        // 获取当前的Subject
        Subject curUser = ShiroHelper.getSubject(request, response);
        curUser.logout();
        log.info("退出loginout方法");
        //记录退出日志
        loginRecordService.saveLogoutRecord(userId,userName,requestIp);
        return ResultVO.success("登出成功");
    }

}
