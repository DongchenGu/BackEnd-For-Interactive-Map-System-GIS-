package com.GIS.boot.Service;

import com.GIS.boot.Dao.DBUserInterface;
import com.GIS.boot.Dao.DBUserInterfaceImpl;
import com.GIS.boot.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    //将dao层属性注入service层
    @Autowired
    private DBUserInterface dbUserInterfaceImpl;


    public String LoginIn(String email, String username, String password, HttpServletRequest request) {
        //根据用户名查询，用户是否存在
        User user = dbUserInterfaceImpl.findUserByEmail(email);
                 //如果存在
                 if(user!=null){
                         if(password.hashCode()==(user.getHashPWD())){
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



    public Boolean Insert(String email, String username,String password) {
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
