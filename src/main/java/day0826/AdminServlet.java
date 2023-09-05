package day0826;

import day0904.MybatisMapper;
import day0904.mybatis.po.User;
import day0905.IDb1Connect;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/admin/user")
public class AdminServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IDb1Connect connector = (IDb1Connect) req.getSession().getServletContext().getAttribute("connector");
        String sPage = req.getParameter("page");
        int page;
        if (sPage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(sPage);
        }
        List<User> userList = connector.getAPageUser((page - 1) * 10);
        int count = connector.getUserCount();
        int pageCount;
        if (count % 10 == 0) {
            pageCount = count / 10;
        } else {
            pageCount = count / 10 + 1;
        }
        req.setAttribute("count", count);
        req.setAttribute("page", page);
        req.setAttribute("pageCount", pageCount);
        req.setAttribute("userList", userList);
        req.getRequestDispatcher("../page/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
