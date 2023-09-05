package day0905;

import day0904.mybatis.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloLog {
    private static Logger logger = LoggerFactory.getLogger(HelloLog.class);
    public static void main(String[] args) {
        logger.info("Hello World {}", new User("", "", ""));
    }
}
