package day0912;

import day0908.MessageDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/article")
public class ArticleServet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        BlogListServiceImpl blogListService = BlogListServiceImpl.getInstance();
        MessageDTO messageDTO = blogListService.articleServlet(id);
        req.setAttribute("content", messageDTO.getMessage());
        req.setAttribute("blog", messageDTO.getObject());
        req.getRequestDispatcher("./page/article-show.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
