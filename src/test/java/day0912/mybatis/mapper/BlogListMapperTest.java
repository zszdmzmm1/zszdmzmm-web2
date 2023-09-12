package day0912.mybatis.mapper;

import day0912.mybatis.po.BlogList;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.DisplayName;
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
        Assertions.assertTrue(blogLists.size() > 0);
    }

    @Test
    public void selectAllTest2() {
        BlogListMapper mapper = sqlSessionFactory.openSession(true).getMapper(BlogListMapper.class);
        List<BlogList> blogLists = mapper.selectAll();
        Assertions.assertNotNull(blogLists.get(1).getPublishTime());
    }

    @Test
    public void selectByIdTest(){
        BlogListMapper mapper = sqlSessionFactory.openSession(true).getMapper(BlogListMapper.class);
        BlogList blogList = mapper.selectById(2);
        Assertions.assertNotNull(blogList.getPublishTime());
    }

    @Test
    public void selectByIdTest2(){
        BlogListMapper mapper = sqlSessionFactory.openSession(true).getMapper(BlogListMapper.class);
        BlogList blogList = mapper.selectById(2);
        Assertions.assertEquals("java的基本数据类型(Primitive Data Types)", blogList.getTitle());
    }

    @Test
    @DisplayName("通过条件搜索文章")
    public void selectByConditionTest(){
        BlogListMapper mapper = sqlSessionFactory.openSession(true).getMapper(BlogListMapper.class);
        List<BlogList> blogLists = mapper.selectByCondition("java", "数据", 1);
        Assertions.assertTrue(blogLists.size() > 0);
    }

}
