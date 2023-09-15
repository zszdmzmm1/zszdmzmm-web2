package day0915.simple.dao.impl;

import day0915.simple.dao.UserDao;

public class SimpleUserDao implements UserDao{

    @Override
    public void showLogin() {
        System.out.println("UserDaoImpl.showLogin...");
    }

    @Override
    public void login() {
        System.out.println("UserDaoImpl.login...");
    }
}
