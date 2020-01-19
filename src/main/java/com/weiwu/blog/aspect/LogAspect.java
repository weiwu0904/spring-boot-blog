package com.weiwu.blog.aspect;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.weiwu.blog.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String method = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestInfo requestInfo = new RequestInfo(url,ip,method,args);
        logger.info("request: {}",requestInfo);
    }

    @After("log()")
    public void after() {
        logger.info("--------------after-------------");
    }

    // 捕获所有返回的内容  Object obj
    @AfterReturning(returning = "obj", pointcut = "log()")
    public void afterReturn(Object obj) {
        logger.info("afterReturn: {}",obj);
    }

    // 封装请求信息
    @ToString
    @AllArgsConstructor
    private static class RequestInfo {
        private String url;
        private String ip;
        private String method;
        private Object args[];
    }
}
