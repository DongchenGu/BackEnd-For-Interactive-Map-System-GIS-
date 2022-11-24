package com.GIS.boot.Filter;


import com.GIS.boot.Service.TokenUtils;



import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ds
 * @Date 2022-06-15
 */

@Order(1)
@Component
public  class MMFilter implements Filter {

    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化");
        // 过滤器初始化
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String,String> map = new HashMap<>();
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,PUT");
        response.setHeader("Access-Control-Max-Age", "0");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
        response.setHeader("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");


        String url =  ((HttpServletRequest)servletRequest).getRequestURI();
        String method = ((HttpServletRequest)servletRequest).getMethod();
        if(url != null){
            //登录请求直接放行
            if("/login".equals(url)){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }else if("/register".equals(url)){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }else if("OPTIONS".equals(method)){
                //必须要放行options请求,
                //查询请求发出前的OPTIONS请求是检查服务器是否支持跨域请求的，它并没有带上headers中的token信息，
                //所以后台在接到OPTIONS请求后获取不到token信息，直接返回了。所以前端也出现跨域情况。
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }else{
                //其他请求验证token
                String token = ((HttpServletRequest)servletRequest).getHeader("authorization");
                //StringUtils.isNotBlank(token)
                //System.out.println(token);
                if(token!=null){

                    //token验证结果
                    int verify  = tokenUtils.verify(token);
                    //System.out.println("verify");
                    //System.out.println(verify);
                    if(verify != 1){
                        //]
                        if(verify == 2){
                            map.put("credentialError","token-expire");
                        }else if(verify == 0){
                            map.put("credentialError","UserInfo-not-match");
                        }
                    }else if(verify  == 1){
                        //验证成功，放行
                        filterChain.doFilter(servletRequest,servletResponse);
                        return;
                    }
                }else{
                    //token为空的返回
                    map.put("credentialError","Error-no-token");
                }
            }
            JSONObject jsonObject = new JSONObject(map);
            response.setContentType("application/json");
            //设置响应的编码
            response.setCharacterEncoding("utf-8");
            //响应
            PrintWriter pw=response.getWriter();
            pw.write(jsonObject.toString());
            pw.flush();
            pw.close();
        }
    }


    public void destroy() {
        // 过滤器销毁
    }
}

