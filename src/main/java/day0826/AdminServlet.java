package day0826;

import day0904.mybatis.po.User;
import day0905.IUserDau;
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
        List<User> userList = null;
        int count;
        int pageCount = 0;
        IUserDau connector = (IUserDau) req.getSession().getServletContext().getAttribute("connector");
        String sPage = req.getParameter("page");
        String email = req.getParameter("sEmail");
        int page;
        if (email == null) {
            email = "";
        }
        if (sPage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(sPage);
        }
        if ("search".equals(req.getSession().getAttribute("mode"))) {
            userList = connector.getUsersByFussyEmailSearch(email, (page - 1) * 10);
            count = connector.getUserCountsByFussyEmailSearch(email);
        } else {
            userList = connector.getAPageUser((page - 1) * 10);
            count = connector.getUserCount();
        }
        if (count % 10 == 0) {
            pageCount = count / 10;
        } else {
            pageCount = count / 10 + 1;
        }
        req.setAttribute("sEmail", email);
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
