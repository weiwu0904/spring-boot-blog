package com.weiwu.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

// Spring Boot中Web Controller 统一异常处理
// controller 方法内出现异常,会被这个方法拦截，我们在这里记录日志，并返回自定义错误页面
@ControllerAdvice
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(Exception.class) // 这个方法可以拦截所有异常信息
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {

        // 记录错误日志 {} 占位符
        logger.error("Request URL：{}，Exception：{}",req.getRequestURL(),e.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",req.getRequestURL());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("error/500");

        return modelAndView;
    }
}
