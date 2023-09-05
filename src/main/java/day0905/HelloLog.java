package day0905;

import day0904.mybatis.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloLog {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(HelloLog.class);
        logger.info("Hello World {}", new User("", "", ""));
    }
}
