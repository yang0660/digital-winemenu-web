package com.myicellar.digitalmenu.controller.app;

import com.myicellar.digitalmenu.dao.entity.UserAccount;
import com.myicellar.digitalmenu.service.UserAccountService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.utils.PrincipalContext;
import com.myicellar.digitalmenu.utils.RequestUtil;
import com.myicellar.digitalmenu.vo.request.SendSmsCodeReqVO;
import com.myicellar.digitalmenu.vo.request.UpdatePasswordReqVO;
import com.myicellar.digitalmenu.vo.request.UploadPushInfoReqVO;
import com.myicellar.digitalmenu.vo.request.UserReqVO;
import com.myicellar.digitalmenu.vo.request.app.UserRegisterReqVO;
import com.myicellar.digitalmenu.vo.response.LoginRespVO;
import com.myicellar.digitalmenu.vo.response.ResultVO;
import com.myicellar.digitalmenu.vo.response.UserListRespVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/app/user")
@Api(tags = "APP用户注册、个人信息", description = "/user")
public class AppUserController {

    @Autowired
    private UserAccountService userAccountService;

    /**
     * 根据ID查询用户信息
     * @param reqVO
     * @return
     */
    @PostMapping("queryById")
    @ApiOperation("根据ID查询用户信息")
    @RequiresRoles(value = {"APP"})
    public ResultVO<UserListRespVO> queryById(@RequestBody UserReqVO reqVO) {
        Long currentUserId = PrincipalContext.getCurrentUserId();
        UserListRespVO user = userAccountService.selectUserInfoById(currentUserId);
        return ResultVO.success(user);
    }

    /**
     * 发送验证码短信
     * @param reqVO
     * @return
     */
    @PostMapping("sendCode")
    @ApiOperation(value = "发送验证码短信")
    @AuthIgnore
    public ResultVO sendCode(@RequestBody SendSmsCodeReqVO reqVO) {
        ResultVO result = userAccountService.sendCodeSms(reqVO);
        return result;
    }

    /**
     * 用户注册
     * @param reqVO
     * @return
     */
    @PostMapping("register")
    @ApiOperation(value = "用户注册")
    @AuthIgnore
    public ResultVO<LoginRespVO> register(@RequestBody UserRegisterReqVO reqVO, HttpServletRequest request, HttpServletResponse response) {
        ResultVO result = userAccountService.register(reqVO);
        if(ResultVO.SUCCESS_CODE.equals(result.getRespCode())){
            UserAccount user = userAccountService.selectByMobile(reqVO.getMobile());
            result = PrincipalContext.checkAppUser(user);
            if(result==null) {
                //登录
                String requestIp = RequestUtil.getUserRealIP(request); //请求的真实IP
                result = userAccountService.postLogin(user,reqVO.getPassword(), requestIp);
            }
        }
        return result;
    }

    /**
     * 修改密码
     * @param reqVO
     * @return
     */
    @PostMapping("updatePassword")
    @ApiOperation(value = "修改密码")
    public ResultVO updatePassword(@RequestBody UpdatePasswordReqVO reqVO) {
        ResultVO result = userAccountService.updatePassword(reqVO);
        return result;
    }

    /**
     * 修改个人信息
     * @param reqVO
     * @return
     */
    @PostMapping("update")
    @ApiOperation(value = "修改个人信息")
    @RequiresRoles(value = {"APP"})
    public ResultVO update(@RequestBody UserReqVO reqVO) {
        ResultVO result = userAccountService.update(reqVO);
        return result;
    }

    /**
     * 上传推送基础数据
     * @param reqVO
     * @return
     */
    @PostMapping("uploadPushInfo")
    @ApiOperation(value = "上传推送基础数据")
    @RequiresRoles(value = {"APP"})
    public ResultVO uploadPushInfo(@RequestBody UploadPushInfoReqVO reqVO) {
        Long userId = PrincipalContext.getCurrentUserId();
        reqVO.setUserId(userId);
        ResultVO result = userAccountService.uploadPushInfo(reqVO);
        return result;
    }

}
