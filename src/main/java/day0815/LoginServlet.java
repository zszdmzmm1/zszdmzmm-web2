package day0815;

import day0904.mybatis.po.User;
import day0904.UserDaoJDBCImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/login-verify")
public class LoginServlet extends HttpServlet {
    UserDaoJDBCImpl userDaoJDBCImpl = UserDaoJDBCImpl.getUserDaoJDBC();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("get...");
        System.out.println(req.getMethod());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userDaoJDBCImpl.getUserByEmail(req.getParameter("email"));
        if(user == null){
            req.setAttribute("user", "未找到该用户！");
        }else{
            if(req.getParameter("password").equals(user.getPassword())){
                if(user.getRole().equals("管理员")){
                    List<User> userList = userDaoJDBCImpl.getAllUser();
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

