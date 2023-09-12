package day0912.mybatis.mapper;

import day0912.mybatis.po.BlogList;

import java.util.List;

public interface BlogListMapper {
    List<BlogList> selectAll();

    BlogList selectById(int id);
}
