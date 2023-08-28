package day0826;

import day0818.JDBCConnection;
import day0818.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;


@WebServlet("/admin/user")
public class AdminServlet extends HttpServlet {
    JDBCConnection jdbcConnection = new JDBCConnection();
    Connection connection = jdbcConnection.getConnection();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sPage = req.getParameter("page");
        int page;
        if (sPage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(sPage);
        }
        List<User> userList = jdbcConnection.getAPageUser(connection, page);
        int count = jdbcConnection.getUserCount(connection);
        int pageCount;
        if(count % 10 == 0){
            pageCount = count / 10;
        }else{
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
