package com.GIS.boot.Controller;


import com.GIS.boot.Service.UserService;
import com.GIS.boot.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@Controller
public class LoginController {

    //将Service注入Web层
    @Resource
    UserService userService;

    @RequestMapping(value="/login")
     public String login(HttpServletRequest request){
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String result =userService.LoginIn(email,username,password,request);
        if(result=="success"){
            return "success";
        }else{
            return "error";
        }
    }


    @RequestMapping("/signup")
    public String disp(){
        return "signup";
    }

    //实现注册功能
    @CrossOrigin(origins = "*",maxAge = 3600)
    @RequestMapping(value = {"/register"}, method = {RequestMethod.POST})
    public String signUp(HttpServletRequest request){
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE,OPTIONS");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "*");

        //response.header('Access-Control-Allow-Headers', 'Content-Type, Content-Length, Authorization, Accept, X-Requested-With , yourHeaderFeild');
//        response.addHeader(  "Access-Control-Allow-Method","POST,GET");//允许访问的方式
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Boolean test = userService.Insert(email,username,password);
        if(test){
            return "success";
        }else{
            return  "false";
        }

    }

}
