package com.GIS.boot.Controller;


import com.GIS.boot.Model.User;
import com.GIS.boot.Model.UserInfo;
import com.GIS.boot.Service.TokenUtils;
import com.GIS.boot.Service.UserService;



import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;


@Slf4j
@RestController
public class LoginController {

    //将Service注入Web层
    @Autowired
    UserService userService;
    @Autowired
    private TokenUtils tokenUtils;


    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST})
    @RequestMapping(value="/login", method = {RequestMethod.POST})
     public String  login(@RequestBody Map<String ,String> userInformation, HttpServletResponse response){

        String email = userInformation.get("email");
        String password = userInformation.get("password");
        JSONObject jsonObject=new JSONObject();

        System.out.println(userInformation.get("email")+" "+userInformation.get("password"));
        Map<String, String> result =userService.LoginIn(email,password);

        if(result.get("tag")=="success"){
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("email", email);
            userInfo.put("password", password);
            userInfo.put("username", result.get("username"));

            //创建并将token返回给用户
            String token =tokenUtils.createToken(email,password);
            jsonObject.put("token", token);
            jsonObject.put("user", userInfo);
            jsonObject.put("success",true);


            return jsonObject.toString();
        }else {
            jsonObject.put("errMsg",result);
            return  jsonObject.toString();
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
