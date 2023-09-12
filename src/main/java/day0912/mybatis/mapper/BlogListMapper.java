package day0912.mybatis.mapper;

import day0912.mybatis.po.BlogList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogListMapper {
    List<BlogList> selectAll();

    BlogList selectById(int id);

    List<BlogList> selectByCondition(@Param("title") String title, @Param("content") String content, @Param("status") int status);
}
