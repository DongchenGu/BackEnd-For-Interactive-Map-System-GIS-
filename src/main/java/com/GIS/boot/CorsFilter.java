package com.GIS.boot;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
@WebFilter("/*")
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse resp, FilterChain chain) throws IOException, ServletException
    {
        HttpServletResponse response = (HttpServletResponse) resp; response.setHeader("Access-Control-Allow-Origin", "*"); //解决跨域访问报错
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600"); //设置过期时间
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, client_id, uuid, Authorization");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 支持HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // 支持HTTP 1.0. response.setHeader("Expires", "0");
        chain.doFilter(request, resp);
    }
    @Override
    public void init(FilterConfig filterConfig) {}
    @Override
    public void destroy() {}
}
