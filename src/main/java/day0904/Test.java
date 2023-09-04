package day0904;

import day0904.mybatis.mapper.UserMapper;
import day0904.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class Test {
    public static void main(String[] args) throws IOException {
        User user = new User("u5", "hjf", 1000);
        String resource = "day0904/mybatis/mybatisConfig.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession session = sqlSessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        int id = mapper.insertUser(user);
        session.commit();
        System.out.println(id);
    }
}
