package com.GIS.boot.Model;

public class UserWithPhoto {
    private String email;
    private String photo;

    public  UserWithPhoto(){

    }

    public UserWithPhoto(String email, String photo){
        this.email = email;
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
