package com.myicellar.digitalmenu.interceptor;

import com.myicellar.digitalmenu.dao.entity.OperationRecord;
import com.myicellar.digitalmenu.service.OperationRecordService;
import com.myicellar.digitalmenu.shiro.AuthIgnore;
import com.myicellar.digitalmenu.shiro.SysLog;
import com.myicellar.digitalmenu.shiro.UserAuthPrincipal;
import com.myicellar.digitalmenu.utils.RequestUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@Component
public class AccessRecordInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    private OperationRecordService operationRecordService;

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            OperationRecord record = new OperationRecord();
            record.setIp(RequestUtil.getUserRealIP(request));
            record.setOperationTime(new Date());
            if (handler instanceof HandlerMethod) {
                HandlerMethod methodHandler = (HandlerMethod) handler;
                //获取访问方法的注解
                SysLog sysLog = methodHandler.getMethod().getAnnotation(SysLog.class);
                AuthIgnore authIgnore = methodHandler.getMethod().getAnnotation(AuthIgnore.class);
                if (sysLog != null && authIgnore == null) {

                    Subject subject = SecurityUtils.getSubject();
                    Session session = subject.getSession(false);
                    if (session != null) {
                        String sessionId = session.getId().toString();
                        UserAuthPrincipal principal = (UserAuthPrincipal) subject.getPrincipal();
                        Long userId = null == principal ? null : Long.valueOf(principal.getPrincipal());
                        record.setUserId(userId);
                        record.setOperationType(sysLog.operation());
                        String roleNames = String.join(",", principal.getRoleNames());
                        record.setRoleName(roleNames);
                        //获取请求的参数
                        operationRecordService.saveOperationRecord(record);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}