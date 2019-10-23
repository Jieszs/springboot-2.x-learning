package com.example.demo.aspect;

import com.example.demo.aspect.validator.UserValidator;
import com.example.demo.aspect.validator.impl.UserValidatorImpl;
import com.example.demo.pojo.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {
    //* 之后一定要有空格
    @Pointcut("execution(* com.example.demo.service.impl.UserServiceImpl.print(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void before(JoinPoint point) {
        System.out.println("before....");
    }

    @Before("pointCut()&&args(user)")
    public void beforeParam(JoinPoint point, User user) {
        Object[] args = point.getArgs();
        System.out.println("beforeParam....");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("after....");
    }

    @AfterReturning("pointCut()")
    public void afterReturning() {
        System.out.println("afterReturning");
    }

    @AfterThrowing("pointCut()")
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }

    //环绕通知
    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("around before...");
        joinPoint.proceed();
        System.out.println("around after...");
    }

    @DeclareParents(value = "com.example.demo.service.impl.UserServiceImpl+", defaultImpl = UserValidatorImpl.class)
    public UserValidator userValidator;
}
