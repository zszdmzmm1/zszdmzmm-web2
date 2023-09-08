package day0824;

import day0904.mybatis.po.User;
import day0905.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao connector = (UserDao) req.getSession().getServletContext().getAttribute("connector");
        int id = Integer.parseInt(req.getParameter("id").substring(1));
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = connector.getUserById(id);
        if("".equals(email)){
            email = user.getEmail();
        }
        if("".equals(password)){
            password = user.getPassword();
        }
        connector.updateUser(id, email, password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
