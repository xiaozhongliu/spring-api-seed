package com.webapi.seed.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger("INTERCEPTOR");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

//        String path = request.getRequestURI();
//        String queryString = request.getQueryString();
//        if (queryString != null) {
//            path = String.format("%s?%s", path, queryString);
//        }
//        logger.info("{} {}", request.getMethod(), path);

        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

//        logger.info("END {}", response.getStatus());

    }

}
