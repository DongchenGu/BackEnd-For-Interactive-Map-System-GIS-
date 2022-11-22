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

    //实现修改用户信息的功能
    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST})
    @RequestMapping(value="/updateProfile", method = {RequestMethod.POST})
    public String updateProfile(@RequestBody Map<String ,String> userInformation, HttpServletResponse response){
        JSONObject jsonObject=new JSONObject();
        String email = userInformation.get("email");
        String password = userInformation.get("password");
        String username = userInformation.get("username");

        Map<String, String> result = userService.Update(email,password,username);
        if(result.get("tag").equals("success")){
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("email", email);
            userInfo.put("username", username);
            //放置用户重登陆标志，一旦用户更改了密码就得重新登录
            if(password!=null){
                userInfo.put("relogin", "true");
            }else{
                userInfo.put("relogin", "false");
            }

            jsonObject.put("user", userInfo);
            jsonObject.put("success",true);
            return jsonObject.toString();
        }else{
            jsonObject.put("errMsg",result);
            return  jsonObject.toString();
        }
    }


    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST} )
    @RequestMapping(value="/updateUserPhoto", method = {RequestMethod.POST})
    public String updateUserPhoto(@RequestBody Map<String ,String> UserPhotoInfo, HttpServletResponse response){
        System.out.println("接收到客户上传头像的请求");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,accept,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,Access-Token,token");

        String email = UserPhotoInfo.get("email");
        String photo = UserPhotoInfo.get("userPhoto");
        JSONObject jsonObject=new JSONObject();

        Map<String,String> result = userService.updateUserPhoto(email,photo);
        if(result.get("tag")=="success"){
            System.out.println("头像上传成功");
            //上传用户头像成功，给客户返回结果
            jsonObject.put("success", true);
            return jsonObject.toString();
        }else{
            System.out.println("头像上传失败");
            jsonObject.put("errMsg",result);
            return  jsonObject.toString();
        }
    }


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
            //userInfo.put("password", password);
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
