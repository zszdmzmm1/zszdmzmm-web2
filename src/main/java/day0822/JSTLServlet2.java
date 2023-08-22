package day0822;

import day0818.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/jstl-2")
public class JSTLServlet2 extends HttpServlet {
    List<User> userList = new ArrayList<User>();
    @Override
    public void init(ServletConfig config) throws ServletException {
        userList.add(new User("Tom", "123456", "cat"));
        userList.add(new User("Jerry", "123456", "mouse"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", userList.get(1).getRole());
        req.getRequestDispatcher("./jsp/day0822/jstl2.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
