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
import java.util.List;

@WebServlet("/login-verify")
public class LoginServlet extends HttpServlet {
    JDBCConnection jdbcConnection = new JDBCConnection();
    Connection connection = jdbcConnection.getConnection();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("get...");
        System.out.println(req.getMethod());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = jdbcConnection.getUser(connection, req.getParameter("email"));
        if(user == null){
            req.setAttribute("user", "未找到该用户！");
        }else{
            if(req.getParameter("password").equals(user.getPassword())){
                if(user.getRole().equals("管理员")){
                    List<User> userList = jdbcConnection.getAllUser(connection);
                    req.setAttribute("user", userList);
                } else{
                    req.setAttribute("user", user);
                }
            } else {
                req.setAttribute("user", "密码错误！");
            }
        }
        req.getRequestDispatcher("./welcome-page").forward(req, resp);
    }
}

