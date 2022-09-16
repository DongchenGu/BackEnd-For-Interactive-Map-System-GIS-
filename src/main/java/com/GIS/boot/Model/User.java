package com.GIS.boot.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Document("USER")
public class User implements Serializable {
    private String username;
    private int  hashPWD;
    private String email;


    public User(){
    }

    public User(String email, String username, int hashPWD){
        this.username=username;
        this.hashPWD= hashPWD;
        this.email = email;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getHashPWD() {
        return hashPWD;
    }

    public void setHashPWD(int hashPWD) {
        this.hashPWD = hashPWD;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }



    public String getEmail() {
        return email;
    }
}
