package com.GIS.boot.Service;

import com.GIS.boot.Dao.DBUserInterface;
import com.GIS.boot.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    //将dao层属性注入service层
    @Autowired
    private DBUserInterface dbUserInterfaceImpl;
    @Autowired
    private  TokenUtils tokenUtils;

    public Map<String, String> Update(String email, String password,String username){
        Map<String, String> AuthReturn = new HashMap<>();
        User updateUser=null;
        int passwordInDataBase = 0;
        int hashNewPassword = 0;
        //根据email查询用户
        User searchUser = dbUserInterfaceImpl.findUserByEmail(email);
        //如果存在
        if(searchUser!=null){
            //看密码是否为空
            if(password==null){
                passwordInDataBase = searchUser.getHashPWD();
                updateUser = new User(email,username,passwordInDataBase);
            }else{
                hashNewPassword= password.hashCode();
                updateUser = new User(email,username,hashNewPassword);
            }

            dbUserInterfaceImpl.updateUser(updateUser);
            AuthReturn.put("tag", "success");

        }else{
            AuthReturn.put("tag", "user-not-exist");
        }
        return  AuthReturn;
    }


    public  Map<String, String> LoginIn(String email, String password) {
        Map<String, String> AuthReturn = new HashMap<>();
        //根据email查询，用户是否存在
        User user = dbUserInterfaceImpl.findUserByEmail(email);
                 //如果存在
                 if(user!=null){
                         if(password.hashCode()==(user.getHashPWD())){
                                 //如果密码正确
                                 logger.info("登录成功");
                                 //因为用户请求登录的时候不包括用户名，我们返回的时候得把对应的用户名也返回回去，后面还需要用
                                 AuthReturn.put("username", user.getUsername());
                                AuthReturn.put("tag", "success");
                                 return AuthReturn;
                             }else{
                                 //如果密码错误
                                 System.out.println("wrong-password");
                                 AuthReturn.put("tag", "wrong-password");
                                 return AuthReturn;
                             }
                     }else{
                         //如果不存在，代码邮箱和密码输入有误
                        System.out.println("user-not-exist");
                        AuthReturn.put("tag", "user-not-exist");
                         return AuthReturn;
                     }
    }



    public Boolean Insert(String email, String username,String password) {
        logger.info(email);
        logger.info(username);
        logger.info("传入的密码");
        logger.info(password);
        User testuser = null;
        //从DB中寻找是否有相同的email注册过
        testuser = dbUserInterfaceImpl.findUserByEmail(email);
        if(testuser != null){
            return false;
        }else{
            int HashPWD = password.hashCode();
            logger.info(password);
            User user = new User(email,username,HashPWD);
            dbUserInterfaceImpl.saveUser(user);
            logger.info("创建成功");
            return true;
        }

    }

}
