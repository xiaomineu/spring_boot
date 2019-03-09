package com.zm.blog.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.zm.blog.web.*.*(..))")
    public void log() { }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint)
    {
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        String url=request.getRequestURI();
        String ip=request.getRemoteAddr();
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        Requestlog requestlog=new Requestlog(url,ip,classMethod,args);
        logger.info("Request : {}",requestlog);
    }

    @After("log()")
    public void doAfter()
    {
        logger.info("-----------doAfter--------------");
    }

    @AfterReturning(returning="result",pointcut="log()")
    public void doAfterReturn(Object result)
    {
        logger.info("Result : {}", result);
    }

    private class Requestlog
    {
        private String url;
        private String ip;
        private String classMethod;//调用方法
        private Object[] args;//请求参数

        @Override
        public String toString() {
            return "Requestlog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }

        public Requestlog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }
    }


}
