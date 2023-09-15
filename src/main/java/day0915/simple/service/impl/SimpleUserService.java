package day0915.simple.service.impl;

import day0915.simple.dao.UserDao;
import day0915.simple.service.UserService;
import org.springframework.beans.factory.InitializingBean;

public class SimpleUserService implements UserService, InitializingBean {
    UserDao userDao;

    public SimpleUserService() {
        System.out.println("SimpleUserService.SimpleUserService");
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


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("SimpleUserService.afterPropertiesSet");
    }
}
