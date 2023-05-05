package com.example.kosrbajnoksag;

public class User {
    private String email;
    private String id;
    private String userName;

    public User(String email, String id, String userName) {
        this.email = email;
        this.id = id;
        this.userName = userName;
    }
    public User(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

