package com.RPGTest.domain;

import java.util.Random;

public class User {
    private String username;
    private int id;
    private String password;

    public User(){
        this.id = creatRandomId();
    }
    public User(String username, String password){
        this.username = username;
        this.id = creatRandomId();
        this.password = password;
    }

    public int creatRandomId(){
        Random r = new Random();
        id = 1000 + r.nextInt(1000);
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
