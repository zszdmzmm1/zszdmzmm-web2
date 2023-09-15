package day0915.simple.service.impl;

import day0915.simple.service.UserService;

public class NopUserService implements UserService {
    @Override
    public void showLoginService() {
        System.out.println("nop---showLoginService...");
    }

    @Override
    public void loginService() {
        System.out.println("nop---loginService...");
    }
}
