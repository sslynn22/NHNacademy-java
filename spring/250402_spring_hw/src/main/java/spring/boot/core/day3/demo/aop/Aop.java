package spring.boot.core.day3.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class Aop {

    @Pointcut("execution(* spring.boot.core.day3.demo.bean.*.*(..))")
    public void allBeanMethods() {}

    @Before("allBeanMethods()")
    public void beforeMethod(JoinPoint joinPoint) {
        log.info("--> {}.{}()",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName());
    }

    @After("allBeanMethods()")
    public void afterMethod(JoinPoint joinPoint) {
        log.info("<-- {}.{}()",
                joinPoint.getTarget().getClass().getSimpleName(),
                joinPoint.getSignature().getName());
    }
}
