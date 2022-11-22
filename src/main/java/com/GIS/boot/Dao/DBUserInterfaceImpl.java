package com.GIS.boot.Dao;

import com.GIS.boot.Model.User;
import com.GIS.boot.Model.UserWithPhoto;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository
public class DBUserInterfaceImpl  implements DBUserInterface {

    @Autowired
    private MongoTemplate mongoTemplate;
//    @Autowired
//    public DBUserInterfaceImpl(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }

    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        Query query=new Query(Criteria.where("email").is(email));
        User user = mongoTemplate.findOne(query , User.class);
        return user;
    }

    @Override
    public void updateUser(User user) {
        Query query=new Query(Criteria.where("email").is(user.getEmail()));
        Update update= new Update().set("username", user.getUsername()).set("hashPWD"
                , user.getHashPWD());
        //更新查询返回结果集的第⼀条
        UpdateResult result =mongoTemplate.updateFirst(query,update,User.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);

    }

    @Override
    public void deleteUserByEmail(String email) {
        Query query=new Query(Criteria.where("email").is(email));
        mongoTemplate.remove(query,User.class);
    }

    @Override
    public void updateUserPhoto(UserWithPhoto userWithPhoto) {
        Query query = new Query(Criteria.where("email").is(userWithPhoto.getEmail()));
        Update update = new Update().set("photo", userWithPhoto.getPhoto());
        mongoTemplate.updateFirst(query, update, UserWithPhoto.class);
        System.out.println("图片已经保存");

    }

    @Override
    public UserWithPhoto findPhotoByEmail(String email) {
        Query query= new Query(Criteria.where("email").is(email));
        UserWithPhoto userWithPhoto = mongoTemplate.findOne(query,UserWithPhoto.class);
        return  userWithPhoto;
    }

    @Override
    public void saveUserPhoto(UserWithPhoto userWithPhoto) {
        mongoTemplate.save(userWithPhoto);
    }


}
