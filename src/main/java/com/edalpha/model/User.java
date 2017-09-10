package com.edalpha.model;

/**
 * Created by kaul on 9/9/17.
 */
public class User {
    private Long id;
    private String name;
    private String password;
    private String userType;


    //getters and setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
