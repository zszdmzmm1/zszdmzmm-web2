package day0905;

import day0904.mybatis.po.User;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class HelloLog {
    public static void main(String[] args) {
        log.info("Hello World {}", new User("", "", ""));
    }
}
