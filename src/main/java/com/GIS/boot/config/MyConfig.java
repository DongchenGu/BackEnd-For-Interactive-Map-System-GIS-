package com.GIS.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration(proxyBeanMethods = true)//确保它是singleton
public class MyConfig implements WebMvcConfigurer {




}
