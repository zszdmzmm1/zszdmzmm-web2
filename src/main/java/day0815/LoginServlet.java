package day0815;

import day0818.User;
import day0904.DruidDemo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/login-verify")
public class LoginServlet extends HttpServlet {
    DruidDemo druidDemo = DruidDemo.getDruidDemo();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("get...");
        System.out.println(req.getMethod());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = druidDemo.getUserByEmail(req.getParameter("email"));
        if(user == null){
            req.setAttribute("user", "未找到该用户！");
        }else{
            if(req.getParameter("password").equals(user.getPassword())){
                if(user.getRole().equals("管理员")){
                    List<User> userList = druidDemo.getAllUser();
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

