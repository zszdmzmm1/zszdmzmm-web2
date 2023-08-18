package day0815;

import day0818.JDBCConnection;
import day0818.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/verify")
public class LoginServlet extends HttpServlet {
    JDBCConnection jdbcConnection = new JDBCConnection();
    Connection connection = jdbcConnection.getConnection();
/*    @Override
    public void init(ServletConfig config) throws ServletException {
        *//*User user1 = new User("admin@qq.com", "secret", "管理员");
        User user2 = new User("user@qq.com", "123456", "用户");
        User user3 = new User("visitor@qq.com", "123456", "用户");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        super.init(config);*//*
        Connection connection = jdbcConnection.getConnection();
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("get...");
        System.out.println(req.getMethod());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = jdbcConnection.getUser(connection, req.getParameter("email"));
        boolean isFound = false;
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
/*        for (User u : userList){
            if (req.getParameter("email").equals(u.getEmail()) && req.getParameter("password").equals(u.getPassword())){
                isFound = true;
                if(u.getRole().equals("管理员")){
                    req.setAttribute("user", userList);
                    req.getRequestDispatcher("./welcome-page").forward(req, resp);
                }else{
                    req.setAttribute("user", u);
                    req.getRequestDispatcher("./welcome-page").forward(req, resp);
                }
            }
        }
        if(!isFound){
            req.setAttribute("user", null);
            req.getRequestDispatcher("./welcome-page").forward(req, resp);
        }*/
    }
}

