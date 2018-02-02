package com.webapi.seed.filter;

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

    private static final Logger logger = LoggerFactory.getLogger("LOGGER");

    @Pointcut("execution(public * com.webapi.seed.controller.*.*(..))")
    public void handling() {
    }

    @Before("handling()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String method = request.getMethod();
        String path = request.getRequestURI();
        String queryString = request.getQueryString();
        if (queryString != null) {
            path = String.format("%s?%s", path, queryString);
        }
        logger.info("START : {} {}", method, path);

        Object[] argsArr = joinPoint.getArgs();
        if (!method.equals("GET") && argsArr.length > 0) {
            logger.info("BODY  : {}", new ObjectMapper().writeValueAsString(argsArr[0]));
        }
    }

    @Around("handling()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        logger.info("TIME  : {} ms", (System.currentTimeMillis() - startTime));
        return result;
    }

    @AfterReturning(returning = "response", pointcut = "handling()")
    public void doAfterReturning(ResponseEntity response) throws Throwable {

        logger.info(
                "RESP  : {} {}\n",
                response.getStatusCode(),
                new ObjectMapper().writeValueAsString(response.getBody())
        );
    }

}
