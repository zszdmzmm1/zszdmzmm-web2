package day0823;

import day0904.mybatis.po.User;
import day0904.UserDaoJDBCImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/homepage")
public class LoginServlet extends HttpServlet {
    UserDaoJDBCImpl userDaoJDBCImpl = UserDaoJDBCImpl.getUserDaoJDBC();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userDaoJDBCImpl.getUserByEmail(req.getParameter("email"));
        String url;
        if(user == null){
            req.setAttribute("message", "未找到该用户！");
            req.setAttribute("type", 1);
            url = "./page/user-info.jsp";
        }else{
            if(req.getParameter("password").equals(user.getPassword())){
                if(user.getRole().equals("管理员")){
                    List<User> userList = userDaoJDBCImpl.getAllUser();
                    req.setAttribute("userList", userList);
                    url = "./page/admin.jsp";
                } else{
                    req.setAttribute("user", user);
                    req.setAttribute("type", 3);
                    url = "./page/user-info.jsp";
                }
            } else {
                req.setAttribute("message", "密码错误！");
                req.setAttribute("type", 1);
                url = "./page/user-info.jsp";
            }
        }
        req.getRequestDispatcher(url).forward(req, resp);
    }
}
