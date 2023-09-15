package day0915.demo;

import day0915.demo.dao.UserDao;
import day0915.demo.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppIoC {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        UserService userService =(UserService) ac.getBean("userService");
        userService.loginService();
        userService.showLoginService();

        //-----------------------------
        System.out.println("-------------");


        UserDao userDao = ac.getBean(UserDao.class);
        userDao.login();
    }
}
