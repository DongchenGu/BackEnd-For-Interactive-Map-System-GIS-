package com.GIS.boot;


import com.GIS.boot.Filter.MMFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@ServletComponentScan
@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run =SpringApplication.run(MainApplication.class,args);
    }

    /**
     * 注册Filter
     * @return
     */
    @Bean
    @Order(1)
    public FilterRegistrationBean getFilterRegistrationBean(){
        FilterRegistrationBean ben = new FilterRegistrationBean(new MMFilter());
        ben.addUrlPatterns(new String[]{"/api/tool/*","*.jsp"});
        return ben;

    }


}
