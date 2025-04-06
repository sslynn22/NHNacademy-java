package com.example.demo.account.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class AccountAop {

    @Pointcut("execution(* com.example.demo.account.service.AuthenticationService.login(..))")
    public void pointCutLogin() {
    }

    @Pointcut("execution(* com.example.demo.account.service.AuthenticationService.logout(..))")
    public void pointCutLogout() {
    }


    @After("pointCutLogin() || pointCutLogout()")
    public void afterLogin(JoinPoint joinpoint) {
        String method = joinpoint.getSignature().getName();
        Object[] args = joinpoint.getArgs();
        log.info("{}({})", method, Arrays.toString(args));
    }
}