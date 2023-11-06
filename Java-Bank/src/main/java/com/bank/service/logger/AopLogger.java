package com.bank.service.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopLogger {

    //@After
//    @Before("execution(* org.telran.validationExample.com.service..*.*(..))")
//    public void advice(JoinPoint joinPoint){
//        System.out.println("Method call: " + joinPoint);
//    }
//
//    @AfterReturning(value = "execution(* org.telran.validationExample.com.service..*.*(..))",
//            returning = "result")
//    public void adviceAfterReturning(JoinPoint joinPoint, Object result){
//        System.out.println("Method call: " + joinPoint + "; exception is: " + result);
//    }
//
//    @AfterThrowing(value = "execution(* org.telran.validationExample.com.service..*.*(..))",
//            throwing = "result")
//    public void adviceAfterThrowing(JoinPoint joinPoint, Throwable result){
//        System.out.println("Method call: " + joinPoint + "; exception is: " + result);
//    }
//
//    @Around(value = "execution(* org.telran.validationExample.com.service..*.*(..))")
//    public Object adviceAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("(@Around)Before method call: " + joinPoint);
//        long startTime = System.currentTimeMillis();
//        Object returnValue = joinPoint.proceed();
//        long timeTaken = System.currentTimeMillis() - startTime;
//        System.out.println("(@Around)Time taken to execute: " + joinPoint + " is " + timeTaken);
//
//        return returnValue;
//    }
}
