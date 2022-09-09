package com.GIS.boot.config;

import com.GIS.boot.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration(proxyBeanMethods = true)//确保它是singleton
public class MyConfig {
    @Bean
    public User user1(){
        return new User("nobert", "12456");
    }


}
