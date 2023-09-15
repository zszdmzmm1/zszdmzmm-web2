package day0915.simple;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import day0915.simple.service.UserService;
import day0915.simple.dao.UserDao;


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
