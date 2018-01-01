package com.webapi.seed.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class RequestLogger {

    private static final Logger logger = LoggerFactory.getLogger("REQUEST");

    @Pointcut("execution(public * com.webapi.seed.controller.*.*(..))")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String path = request.getRequestURI();
        String queryString = request.getQueryString();
        if (queryString != null) {
            path = String.format("%s?%s", path, queryString);
        }
        logger.info("START : {} {}", request.getMethod(), path);

        Object[] argsArr = joinPoint.getArgs();
        if (argsArr.length > 0) {
            logger.info("BODY  : {}", new ObjectMapper().writeValueAsString(argsArr[0]));
        }
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        logger.info("TIME  : {} ms", (System.currentTimeMillis() - startTime));
        return result;
    }

    @AfterReturning(returning = "response", pointcut = "logPointCut()")
    public void doAfterReturning(ResponseEntity response) throws Throwable {

        logger.info(
                "END   : {} {}\n",
                response.getStatusCode(),
                new ObjectMapper().writeValueAsString(response.getBody())
        );
    }

}
