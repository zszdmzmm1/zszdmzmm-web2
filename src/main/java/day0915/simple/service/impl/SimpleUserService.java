package day0915.simple.service.impl;

import day0915.simple.dao.UserDao;
import day0915.simple.service.UserService;

public class SimpleUserService implements UserService {
    UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void showLoginService() {
        userDao.showLogin();
        System.out.println("showLoginService...");
    }

    @Override
    public void loginService() {
        userDao.login();
        System.out.println("loginService...");
    }
}
