package com.imooc.luckymoney.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sun.rmi.runtime.Log;

import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    //切点
    @Pointcut("execution(public * com.imooc.luckymoney.controller.LuckymoneyController.*(..))")
    public void log(){

    }

    //方法执行之前拦截
    @Before("log()")
    public void doBefore(JoinPoint joinpoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        //url 记录请求的方式
        logger.info("url={}",request.getRequestURL());

        //method
        logger.info("method={}",request.getMethod());

        //ip 客户端的ip
        logger.info("ip={}",request.getRemoteAddr() );

        //类方法
        logger.info("class_name={}",joinpoint.getSignature().getDeclaringTypeName() + "." + joinpoint.getSignature().getName());

        //参数
        logger.info("args={}",joinpoint.getArgs());
    }

    @After("log()")
    public void doAfter(){
        logger.info("2222222222222");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object){
        logger.info("response={}",object.toString());
    }
}
