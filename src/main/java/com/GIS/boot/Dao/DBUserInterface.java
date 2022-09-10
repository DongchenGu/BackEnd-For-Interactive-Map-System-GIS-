package com.GIS.boot.Dao;

import com.GIS.boot.bean.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DBUserInterface extends MongoRepository<User, String> {

    //findByUsername 命名有研究，比如 findBy后面的名称是实体类属性名称

    public User findByname(String username);
}
