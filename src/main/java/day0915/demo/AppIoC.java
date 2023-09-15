package day0915.demo;

import day0915.demo.service.UserService;
import day0915.demo.service.impl.NopUserService;
import day0915.demo.service.impl.SimpleUserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppIoC {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        UserService userService =(UserService) ac.getBean("userService");
        userService.loginService();
        userService.showLoginService();

        //-----------------------------
        System.out.println("-------------");


        NopUserService nopUserService = ac.getBean(NopUserService.class);
        nopUserService.showLoginService();
        nopUserService.loginService();
    }
}
