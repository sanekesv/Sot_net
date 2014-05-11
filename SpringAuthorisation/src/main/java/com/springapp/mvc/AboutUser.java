package com.springapp.mvc;

/**
 * Created by Александр on 28.04.14.
 */
public class AboutUser {
    public String username;
    public int id;
    public String password;
    public String imageName;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
//
//    @Override
//    public String toString() {
//        return imageName+"<br><a href=\"/user?login="+username+"\">"+username+"</a>\n<br>\n";
//    }
}
