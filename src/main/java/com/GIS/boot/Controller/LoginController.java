package com.GIS.boot.Controller;


import com.GIS.boot.Service.UserService;
import com.GIS.boot.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Slf4j
@Controller
public class LoginController {

    //将Service注入Web层
    @Resource
    UserService userService;

    @RequestMapping(value="/login")
     public String login(HttpServletRequest request){
        //获取用户和密码
                 String username = request.getParameter("username");
                 String password = request.getParameter("password");
        String result =userService.LoginIn(username,password,request);
        if(result=="success"){
            return "success";
        }else{
            return "error";
        }
    }

//    @RequestMapping(value = "/loginIn",method = RequestMethod.POST)
//    public String login(String name,String password){
//        User userBean = userService.LoginIn(name, password);
//        log.info("name:{}",name);
//        log.info("password:{}",password);
//        if(userBean!=null){
//            return "success";
//        }else {
//            return "error";
//        }
//    }
    @RequestMapping("/signup")
    public String disp(){
        return "signup";
    }

    //实现注册功能
    @RequestMapping(value = "/register")
    public String signUp(String name,String password){
        userService.Insert(name, password);
        return "success";
    }

}
