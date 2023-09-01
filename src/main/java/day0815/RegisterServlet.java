package day0815;

import day0818.JDBCConnection;
import day0818.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/register-verify")
public class RegisterServlet extends HttpServlet {
    JDBCConnection jdbcConnection = JDBCConnection.getJDBCConnection();
    Connection connection = JDBCConnection.getConnection();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("get...");
        System.out.println(req.getMethod());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = jdbcConnection.getUserByEmail(connection, req.getParameter("email"));
        if(user != null){
            req.setAttribute("user", "该用户已存在！");
        }else {
            user = new User(req.getParameter("email"), req.getParameter("password"), "用户");
            jdbcConnection.add(connection, user);
            req.setAttribute("user", user);
        }
        req.getRequestDispatcher("./welcome-page").forward(req, resp);
    }
}
