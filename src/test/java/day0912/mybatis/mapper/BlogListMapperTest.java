package day0912.mybatis.mapper;

import day0912.mybatis.po.BlogList;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BlogListMapperTest {
    private static SqlSessionFactory sqlSessionFactory;
    @BeforeAll
    public static void createSqlSessionFactory() throws IOException {
        String resource = "day0912/mybatis/mybatisConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test() {
        Assertions.assertTrue(true);
    }

    @Test
    public void selectAllTest() {
        BlogListMapper mapper = sqlSessionFactory.openSession(true).getMapper(BlogListMapper.class);
        List<BlogList> blogLists = mapper.selectAll();
        System.out.println(blogLists.get(1));
        Assertions.assertTrue(blogLists.size() > 0);
        Assertions.assertNull(blogLists.get(1).getPublishTime());
    }

}
