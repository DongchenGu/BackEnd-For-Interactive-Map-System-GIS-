package com.GIS.boot.Service;


import com.GIS.boot.Dao.DBUserInterface;
import com.GIS.boot.bean.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    //将dao层属性注入service层
    @Autowired
    private  DBUserInterface DBUserInterface;


    public String LoginIn(String name, String password, HttpServletRequest request) {
        //根据用户名查询，用户是否存在
        User user = DBUserInterface.findByname(name);
                 //如果存在
                 if(user!=null){
                         if(password.equals(user.getPassword())){
                                 //如果密码正确
                                 //将用户信息放入到会话中...
                                 request.setAttribute("user", user);
                                 return "success";
                             }else{
                                 //如果密码错误
                                 System.out.println("wrong-password");
                                 return "wrong-password";
                             }
                     }else{
                         //如果不存在，代码邮箱和密码输入有误
                        System.out.println("user-not-exit");
                         return "user-not-exit";
                     }
    }



    public User Insert(String name,String password) {
        User user = new User(name,password);
        return DBUserInterface.insert(user);

    }

}
