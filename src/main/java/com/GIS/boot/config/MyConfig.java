package com.GIS.boot.config;

import com.GIS.boot.CorsFilter;
import com.GIS.boot.Filter.MMFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration(proxyBeanMethods = true)//确保它是singleton
public class MyConfig implements WebMvcConfigurer {




}
