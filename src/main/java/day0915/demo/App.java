package day0915.demo;

import day0915.demo.controller.UserController;

public class App {
    public static void main(String[] args) {
        UserController userController = new UserController();
        userController.showLogin();
        userController.login();
    }
}
