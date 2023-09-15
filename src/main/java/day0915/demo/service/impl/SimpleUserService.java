package day0915.demo.service.impl;

import day0915.demo.dao.UserDao;
import day0915.demo.dao.impl.UserDaoImpl;
import day0915.demo.service.UserService;

public class SimpleUserService implements UserService {
    UserDao userDao = new UserDaoImpl();
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
