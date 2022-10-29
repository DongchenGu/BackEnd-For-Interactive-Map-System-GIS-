package com.GIS.boot.Dao;

import com.GIS.boot.Model.User;
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
}
