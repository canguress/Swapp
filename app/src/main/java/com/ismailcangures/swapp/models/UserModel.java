package com.ismailcangures.swapp.models;

public class UserModel {
    private String username, phone, password;

    public UserModel()
    {

    }

    public UserModel(String username, String phone, String password) {
        this.username = username;
        this.phone = phone;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
