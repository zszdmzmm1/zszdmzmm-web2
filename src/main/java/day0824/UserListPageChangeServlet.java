package day0824;

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

@WebServlet("/page-change")
public class UserListPageChangeServlet extends HttpServlet {
    JDBCConnection jdbcConnection = JDBCConnection.getJDBCConnection();
    Connection connection = JDBCConnection.getConnection();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(1111);
        int page = Integer.parseInt(req.getParameter("page"));
        String target = req.getParameter("target");
        if(target.equals("Previous")){
            page -= 1;
        }else{
            page += 1;
        }
        List<User> userList = jdbcConnection.getAPageUser(connection, page);
        req.setAttribute("userList", userList);
        req.setAttribute("page", page);
        req.getRequestDispatcher("./page/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
