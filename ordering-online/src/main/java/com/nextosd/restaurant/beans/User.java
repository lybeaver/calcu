package com.nextosd.restaurant.beans;

import java.util.Date;

public class User {
    private Integer userId;

    private String userName;

    private String password;

    private Integer userType;

    private Date logTime;

    public User(Integer userId, String userName, String password, Integer userType, Date logTime) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.logTime = logTime;
    }

    public User() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }
}