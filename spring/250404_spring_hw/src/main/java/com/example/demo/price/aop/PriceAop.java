package com.example.demo.price.aop;


import com.example.demo.account.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class PriceAop {

    private final AuthenticationService authenticationService;

    @Pointcut("within(com.example.demo.shell.MyCommands)")
    public void myCommandsPointcut() {}

    @Pointcut("execution(* com.example.demo.shell..city(..)) || execution(* com.example.demo.shell..sector(..)) || execution(* com.example.demo.shell..price(..)) || execution(* com.example.demo.shell..billTotal(..))")
    public void pointcut() {}

    @Around("pointcut() && myCommandsPointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        if (authenticationService.getCurrentAccount() == null) {
            throw new RuntimeException("Account not found");
        }
        log.info("----- {} {}.{}({}) ----->",authenticationService.getCurrentAccount().getName(), joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), joinPoint.getArgs());
        Object result = joinPoint.proceed();
        log.info("<----- {} {}.{}({}) -----", authenticationService.getCurrentAccount().getName(), joinPoint.getTarget().getClass(), joinPoint.getSignature().getName(), result.toString());
        return result;
    }
}