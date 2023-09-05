package day0904;

import day0904.mybatis.mapper.UserMapper;
import day0904.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;


public class MybatisMapper {
/*    private MybatisMapper() {
    }

    private static final class MybatisMapperHolder{
        private static final MybatisMapper MYBATIS_MAPPER = new MybatisMapper();
    }

    public static MybatisMapper getMybatisMapper(){
        return MybatisMapperHolder.MYBATIS_MAPPER;
    }*/
    public static UserMapper mapper;
    static  {
        String resource = "day0904/mybatis/mybatisConfig.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession(true);
        mapper = session.getMapper(UserMapper.class);
    }
}
