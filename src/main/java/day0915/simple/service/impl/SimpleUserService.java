package day0915.simple.service.impl;

import day0915.simple.dao.UserDao;
import day0915.simple.service.UserService;

public class SimpleUserService implements UserService {
    UserDao userDao;

    public SimpleUserService() {
        System.out.println("SimpleUserService.SimpleUserService");
    }

    public void init(){
        System.out.println("SimpleUserService.init");
    }

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

    public void destroy(){
        System.out.println("SimpleUserService.destroy");
    }
}
