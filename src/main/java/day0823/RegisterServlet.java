package day0823;

import day0818.JDBCConnection;
import day0818.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    JDBCConnection jdbcConnection = new JDBCConnection();
    Connection connection = jdbcConnection.getConnection();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = jdbcConnection.getUser(connection, req.getParameter("email"));
        if(user != null){
            req.setAttribute("message", "该用户已存在！");
            req.setAttribute("type", 1);
            req.getRequestDispatcher("./page/user-info.jsp").forward(req, resp);
        }else {
            user = new User(req.getParameter("email"), req.getParameter("password"), "用户");
            jdbcConnection.add(connection, user);
            resp.sendRedirect("./page/login.html");
        }
    }
}
