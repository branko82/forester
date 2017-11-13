package com.rhcloud.forester.dao;

public class UserDao {

    public boolean login(String loginName, String password) {

        if (loginName.equalsIgnoreCase("dragan") && password.equalsIgnoreCase("dragan")) {
            return true;
        } else {
            return false;
        }
    }
}