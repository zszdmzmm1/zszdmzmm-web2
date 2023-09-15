package day0915.simple.dao.impl;

import day0915.simple.dao.UserDao;

public class NopUserDao implements UserDao {
    @Override
    public void showLogin() {
        System.out.println("NopUserDao.showLogin");
    }

    @Override
    public void login() {
        System.out.println("NopUserDao.login");
    }
}
