package day0915.demo.controller;

import day0915.demo.service.UserService;
import day0915.demo.service.impl.UserServiceImpl;

public class UserController {
    UserService userService = new UserServiceImpl();

    public void showLogin() {
        userService.showLoginService();
        System.out.println("showLogin...");
    }

    public void login() {
        userService.loginService();
        System.out.println("login...");
    }
}
