package com.GIS.boot.Dao;

import com.GIS.boot.Model.User;
import com.GIS.boot.Model.UserWithPhoto;


public interface DBUserInterface {

    public void saveUser(User user);

    public User findUserByEmail(String email);

    public void updateUser(User user);

    public void deleteUserByEmail(String email);

    public  void  updateUserPhoto(UserWithPhoto userWithPhoto);

    public UserWithPhoto findPhotoByEmail(String email);

    public  void saveUserPhoto(UserWithPhoto userWithPhoto);
}
