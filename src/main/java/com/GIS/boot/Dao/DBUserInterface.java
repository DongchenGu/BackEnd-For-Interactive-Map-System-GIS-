package com.GIS.boot.Dao;

import com.GIS.boot.Model.User;


public interface DBUserInterface {

    public void saveUser(User user);

    public User findUserByEmail(String email);

    public void updateUser(User user);

    public void deleteUserByEmail(String email);
}
