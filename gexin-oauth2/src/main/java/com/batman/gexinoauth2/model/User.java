package com.batman.gexinoauth2.model;

import org.springframework.stereotype.Component;

/**
 * @author liusongwei
 * @Title: User
 * @Description: TODO
 * @date 2018/11/1615:13
 */
@Component
public class User {

    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
