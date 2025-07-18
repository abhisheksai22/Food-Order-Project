package com.infy.food.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new com.infy.food.interceptors.LoggingInterceptors())
                .addPathPatterns("/v1/**")
                .excludePathPatterns("/actuator/**");
    }

}
