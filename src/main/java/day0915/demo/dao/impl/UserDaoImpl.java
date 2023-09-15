package day0915.demo.dao.impl;

import day0915.demo.dao.UserDao;

public class UserDaoImpl implements UserDao {
    @Override
    public void showLogin() {
        System.out.println("UserDaoImpl.showLogin...");
    }

    @Override
    public void login() {
        System.out.println("UserDaoImpl.login...");
    }
}
