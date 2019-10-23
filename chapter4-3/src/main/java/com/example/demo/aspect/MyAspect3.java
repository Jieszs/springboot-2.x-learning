package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
@Aspect
public class MyAspect3 {
    //* 之后一定要有空格
    @Pointcut("execution(* com.example.demo.service.impl.UserServiceImpl.manyAspect(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before() {
        System.out.println("MyAspect3 before....");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("MyAspect3 after....");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("MyAspect3 afterReturning");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("MyAspect3 afterThrowing");
    }

    //环绕通知
    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("MyAspect3 around before...");
        joinPoint.proceed();
        System.out.println("MyAspect3 around after...");
    }

}
