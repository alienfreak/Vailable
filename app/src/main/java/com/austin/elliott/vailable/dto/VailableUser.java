package com.austin.elliott.vailable.dto;

/**
 * Created by Tiger on 8/6/17.
 */

public class VailableUser {
    private String name;
    private int age = -1; //currently this information is unavailable
    private String pictureUri;
    private String email; //currently this information is unavailable

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

    public String getPictureUri() {
        return pictureUri;
    }

    public void setPictureUri(String picture_key) {
        this.pictureUri = picture_key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
