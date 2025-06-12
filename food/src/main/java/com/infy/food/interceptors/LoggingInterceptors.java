package com.infy.food.interceptors;

import com.infy.food.serviceimpl.XRequestId;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class LoggingInterceptors implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // this should be done at api gateway level using filters.
        if (request.getHeader("x-request-id") == null || request.getHeader("x-request-id").isEmpty()) {
            XRequestId.generateXRequestID();
            request.setAttribute("x-request-id", XRequestId.getXRequestID());
        } else {
            XRequestId.X_REQUEST_ID.set(request.getHeader("x-request-id"));
        }

        log.info("Incoming request: {} {} from IP: {}", request.getMethod(), request.getRequestURI(), request.getRemoteAddr());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
    /*@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        XRequestId.clearXRequestID();
        log.info("Request completed: {} {} with status: {}", request.getMethod(), request.getRequestURI(), response.getStatus());
    }

}
