package com.ftn.sbnz.service.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.ftn.sbnz.service.core.error.RestExceptionHandler) && " +
            "!execution(* com.ftn.sbnz.service.core.error.RestExceptionHandler.handleInternalServer(..))")
    private void exceptionHandlerPointcut() { }

    @Async
    @Before("exceptionHandlerPointcut()")
    protected void logException(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Exception ex = (Exception) args[0];
        logger.warn(ex.getMessage(), ex);
    }

    @Pointcut("execution(* com.ftn.sbnz.service.security.rest.RestAuthenticationEntryPoint.commence(..))")
    private void authenticationExceptionHandlerPointcut() { }

    @Async
    @Before("authenticationExceptionHandlerPointcut()")
    protected void logAuthenticationException(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Exception ex = (Exception) args[2];
        logger.warn(ex.getMessage(), ex);
    }

    @Pointcut("execution(* com.ftn.sbnz.service.core.error.RestExceptionHandler.handleInternalServer(..))")
    private void internalServerErrorHandlerPointcut() { }

    @Async
    @Before("internalServerErrorHandlerPointcut()")
    protected void logInternalServerError(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Exception ex = (Exception) args[0];
        logger.error(ex.getMessage(), ex);
    }
}
