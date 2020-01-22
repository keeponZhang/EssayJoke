package com.zhang.app.aop;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TestAnnoAspect {

    @Pointcut("execution(* com.zhang.app.aop.AopActivity.testaop(..))")
    public void pointcut() {
        Log.e("TAG", "TestAnnoAspect pointcut:");
    }    

    @Before("pointcut()")
    public void before(JoinPoint point) {
        Log.e("TAG", "TestAnnoAspect before:");
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e("TAG", "TestAnnoAspect around:");
    }


    @After("pointcut()")
    public void after(JoinPoint point) {
        Log.e("TAG", "TestAnnoAspect after:");
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint point, Object returnValue) {
        Log.e("TAG", "TestAnnoAspect afterReturning:");
    }
    @Around("execution(* com.zhang.app.aop.AopActivity.testaop1(..))")
    public void testaop1(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed();
        Log.e("TAG", "TestAnnoAspect testaop1 around:");
    }
    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        Log.e("TAG", "TestAnnoAspect afterThrowing:" + "ex = " + ex.getMessage());
    }
}