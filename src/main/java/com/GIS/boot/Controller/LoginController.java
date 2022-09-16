package com.GIS.boot.Controller;


import com.GIS.boot.Model.User;
import com.GIS.boot.Model.UserInfo;
import com.GIS.boot.Service.UserService;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Slf4j
@RestController
public class LoginController {

    //将Service注入Web层
    @Autowired
    UserService userService;

    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST})
    @RequestMapping(value="/login", method = {RequestMethod.POST})
     public String login(@RequestBody Map<String ,String> userInformation){

        System.out.println(userInformation.get("email")+" "+userInformation.get("password"));
        String result =userService.LoginIn(userInformation.get("email"),userInformation.get("password"));
        if(result.equals("success")){
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
    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST})
    @RequestMapping(value = {"/register"}, method = {RequestMethod.POST})
    public String signUp(@RequestBody UserInfo UserInfo){
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE,OPTIONS");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "*");

//        String email = request.getParameter("email");
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");

        Boolean test = userService.Insert(UserInfo.getEmail(),UserInfo.getUsername(),UserInfo.getPassword());
        if(test){
            return "success";
        }else{
            return  "false";
        }

    }

}
