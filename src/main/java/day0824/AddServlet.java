package day0824;

import day0904.mybatis.po.User;
import day0904.DruidDemo;
import day0904.MybatisMapper;
import day0905.IDb1Connect;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IDb1Connect connector = (IDb1Connect) req.getSession().getServletContext().getAttribute("connector");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if("".equals(email) || "".equals(password)){
            resp.sendError(400);
            return;
        }
        User user = new User(email, password, "");
        connector.addUser(user);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
