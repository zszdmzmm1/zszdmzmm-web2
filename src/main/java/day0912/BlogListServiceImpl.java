package day0912;

import day0908.MessageDTO;
import day0912.mybatis.mapper.BlogListMapper;
import day0912.mybatis.po.BlogList;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.commonmark.Extension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class BlogListServiceImpl {
    private static final BlogListMapper mapper;

    private BlogListServiceImpl() {
    }

    private static final class BlogListServiceImplHolder {
        private static final BlogListServiceImpl BLOG_LIST_SERVICE = new BlogListServiceImpl();
    }

    public static BlogListServiceImpl getInstance() {
        return BlogListServiceImplHolder.BLOG_LIST_SERVICE;
    }

    static {
        String resource = "day0912/mybatis/mybatisConfig.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        mapper = sqlSessionFactory.openSession(true).getMapper(BlogListMapper.class);
    }

    public List<BlogList> articleList() {
        return mapper.selectAll();
    }

    public MessageDTO articleServlet(int id) {
        BlogList blogList = mapper.selectById(id);
        List<Extension> extensions = List.of(HeadingAnchorExtension.create());
        Parser parser = Parser.builder().extensions(extensions).build();
        Node document = parser.parse(blogList.getContent());
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(extensions).build();
        String html = renderer.render(document);
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(html);
        messageDTO.setObject(blogList);
        return messageDTO;
    }


}
