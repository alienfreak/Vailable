package com.austin.elliott.vailable.dto;

/**
 * Created by Tiger on 8/6/17.
 */

public class VailableUser {
    private String name;
    private int age = -1;
    private String picture_key;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPicture_key() {
        return picture_key;
    }

    public void setPicture_key(String picture_key) {
        this.picture_key = picture_key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
