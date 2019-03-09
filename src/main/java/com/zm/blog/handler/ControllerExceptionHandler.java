package com.zm.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {//拦截所有异常

    private final Logger logger= LoggerFactory.getLogger(this.getClass());//拿到对象

    @ExceptionHandler(Exception.class)//注解表示做异常处理
    public ModelAndView exceptionHander(HttpServletRequest request, Exception e) throws Exception {//request获取异常的url;
        logger.error("Request URL : {}, Exception :{}",request.getRequestURL(),e);//日志记录异常

        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null)
        {
            throw e;
        }
        ModelAndView mv=new ModelAndView();
        mv.addObject("url",request.getRequestURL());//获取的url
        mv.addObject("exception",e);//记录异常信息;
        mv.setViewName("/error/error");//返回至页面;
        return mv;
    }
}
