package day0912.mybatis.mapper;

import day0912.mybatis.po.BlogList;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void selectByIdTest() {
        BlogListMapper mapper = sqlSessionFactory.openSession(true).getMapper(BlogListMapper.class);
        BlogList blogList = mapper.selectById(2);
        Assertions.assertNotNull(blogList.getPublishTime());
    }

    @Test
    public void selectByIdTest2() {
        BlogListMapper mapper = sqlSessionFactory.openSession(true).getMapper(BlogListMapper.class);
        BlogList blogList = mapper.selectById(2);
        Assertions.assertEquals("java的基本数据类型(Primitive Data Types)", blogList.getTitle());
    }

    @Test
    @DisplayName("通过条件搜索文章")
    public void selectByConditionTest() {
        BlogListMapper mapper = sqlSessionFactory.openSession(true).getMapper(BlogListMapper.class);
        List<BlogList> blogLists = mapper.selectByCondition("java", "数据", 1);
        Assertions.assertTrue(blogLists.size() > 0);
    }

    @Test
    @DisplayName("通过条件搜索文章")
    public void selectByConditionTest2() {
        BlogListMapper mapper = sqlSessionFactory.openSession(true).getMapper(BlogListMapper.class);
        BlogList blogList = new BlogList();
        blogList.setTitle("java");
        blogList.setContent("数据");
        blogList.setStatus(1);
        List<BlogList> blogLists = mapper.selectByCondition(blogList);
        Assertions.assertTrue(blogLists.size() > 0);
    }

    @Test
    @DisplayName("通过条件搜索文章")
    public void selectByConditionTest3() {
        BlogListMapper mapper = sqlSessionFactory.openSession(true).getMapper(BlogListMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("title", "java");
        map.put("content", "数据");
        map.put("status", 1);
        List<BlogList> blogLists = mapper.selectByCondition(map);
        Assertions.assertTrue(blogLists.size() > 0);
    }


    @Test
    public void selectByConditionWithDynamicSqlTest() {
        BlogListMapper mapper = sqlSessionFactory.openSession().getMapper(BlogListMapper.class);
        BlogList blogList = new BlogList();
        blogList.setStatus(1);
        List<BlogList> blogLists = mapper.selectByConditionWithDynamicSql(blogList);
        Assertions.assertEquals(1, blogLists.get(0).getStatus());
    }

    @Test
    public void selectByConditionWithOneConditionTest() {
        BlogListMapper mapper = sqlSessionFactory.openSession().getMapper(BlogListMapper.class);
        BlogList blogList = new BlogList();
        blogList.setTitle("数据");
        List<BlogList> blogLists = mapper.selectByConditionWithOneCondition(blogList);
        Assertions.assertEquals(2, blogLists.size());
    }


    @Test
    public void addTest() {
        BlogListMapper mapper = sqlSessionFactory.openSession().getMapper(BlogListMapper.class);
        BlogList blogList = new BlogList();
        blogList.setUserId(1);
        blogList.setTitle("title");
        blogList.setPublishTime("2023-09-11 11:28:47");
        blogList.setLastUpdateTime("2023-09-11 11:28:47");
        blogList.setContent("正文");
        blogList.setDescription("简短描述");
        blogList.setStatus(1);
        mapper.add(blogList);
        List<BlogList> blogLists = mapper.selectAll();
        Assertions.assertTrue(blogLists.size() > 3);
    }


    @Test
    public void updateTest() {
        BlogListMapper mapper = sqlSessionFactory.openSession().getMapper(BlogListMapper.class);


        BlogList blogList = new BlogList();
        blogList.setUserId(1);
        blogList.setTitle("title");
        blogList.setPublishTime("2023-09-11 11:28:47");
        blogList.setLastUpdateTime("2023-09-11 11:28:47");
        blogList.setContent("正文");
        blogList.setDescription("简短描述");
        blogList.setStatus(1);
        mapper.add(blogList);


        //-------------------------------------


        BlogList blogList1 = new BlogList();
        blogList1.setTitle("");
        blogList1.setContent("正文1");
        blogList1.setDescription("简短描述");
        blogList1.setStatus(1);
        mapper.update(blogList.getId(), blogList1);
        BlogList blogList2 = mapper.selectById(blogList.getId());
        Assertions.assertEquals("title", blogList2.getTitle());
        Assertions.assertEquals("正文1", blogList2.getContent());
    }

    @Test
    public void deleteByIdTest() {
        BlogListMapper mapper = sqlSessionFactory.openSession().getMapper(BlogListMapper.class);


        BlogList blogList = new BlogList();
        blogList.setUserId(1);
        blogList.setTitle("title");
        blogList.setPublishTime("2023-09-11 11:28:47");
        blogList.setLastUpdateTime("2023-09-11 11:28:47");
        blogList.setContent("正文");
        blogList.setDescription("简短描述");
        blogList.setStatus(1);
        mapper.add(blogList);

//__________________________________________________


        mapper.deleteById(blogList.getId());
        blogList = mapper.selectById(blogList.getId());
        Assertions.assertNull(blogList);
    }


    @Test
    public void deleteByIdsTest() {
        BlogListMapper mapper = sqlSessionFactory.openSession().getMapper(BlogListMapper.class);


        BlogList blogList = new BlogList();
        blogList.setUserId(1);
        blogList.setTitle("title");
        blogList.setPublishTime("2023-09-11 11:28:47");
        blogList.setLastUpdateTime("2023-09-11 11:28:47");
        blogList.setContent("正文");
        blogList.setDescription("简短描述");
        blogList.setStatus(1);
        int[] ids = new int[10];
        for (int i = 0; i < 10; i++) {
            ids[i] = i;
            mapper.add(blogList);
        }

//        _____________________________________________


        mapper.deleteByIds(ids);
        for (int i = 0; i < ids.length; i++) {
            BlogList blogList1 = mapper.selectById(i);
            Assertions.assertNull(blogList1);
        }
    }
}
