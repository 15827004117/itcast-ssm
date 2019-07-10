package com.ssm.controller;

import com.ssm.domain.SysLog;
import com.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Aop日志
 */
@Aspect
@Component
public class LogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;

    private Date visitTIme;  // 访问开始时间
    private Class clazz;     // 访问的对象
    private Method method;   // 访问的方法

    /**
     * 前置通知
     * 获取开始时间、执行那个方法
     */
    @Before("execution(* com.ssm.controller.*.*(..))")      //拦截所有controller方法
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {

        // 获得访问开始时间
        visitTIme = new Date();

        // 获得访问对象
        clazz = jp.getTarget().getClass();

        // 获得访问方法
        String methodName = jp.getSignature().getName(); // 方法名称
        Object[] args = jp.getArgs(); // 方法参数
        if(args == null || args.length == 0) {
            method = clazz.getMethod(methodName); // 只能获取无参方法
        }else {
            Class[] classArgs = new Class[args.length];  // 获取有参数的方法
            for(int i = 0; i <args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            Method method = clazz.getMethod(methodName, classArgs);
        }
    }

    /**
     * 后置通知
     * 获取访问时长、url、ip、 操作者
     */
    @After("execution(* com.ssm.controller.*.*(..))")      //拦截所有controller方法
    public void doAfter(JoinPoint jp) {

        // 获取访问时长
        long time = new Date().getTime() - visitTIme.getTime();

        // 获取url
        String url = "";
        if(clazz != null && method != null && clazz != LogAop.class){
            // 1.获取类上的@RequestMapping对象
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(clazzAnnotation != null) {
                String[] clazzValue = clazzAnnotation.value();
                // 2.获取方法上的RequestMapping
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation !=null) {
                    String[] methodValue = methodAnnotation.value();
                    url = clazzValue[0] + methodValue[0];
                }
            }
        }
        // 获取访问ip
        String ip = request.getRemoteAddr();

        // 获取操作者
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        // 封装到实体类，执行数据库操作
        SysLog log = new SysLog();
        log.setExecutionTime(time);
        log.setIp(ip);
        log.setMethod("[类]：" + clazz.getName() + " " + "[方法]:" + method.getName());
        log.setUrl(url);
        log.setUsername(username);
        log.setVisitTime(visitTIme);
        try {
            sysLogService.save(log);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
