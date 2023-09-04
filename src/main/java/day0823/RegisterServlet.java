package day0823;

import com.alibaba.fastjson.JSONObject;
import day0818.JDBCConnection;
import day0818.User;
import day0904.DruidDemo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    DruidDemo druidDemo = DruidDemo.getDruidDemo();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = druidDemo.getUserByEmail(req.getParameter("email"));
        HttpSession session = req.getSession();
        if (user != null) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", "该用户已存在");
            session.setAttribute("user", null);
            PrintWriter out = resp.getWriter();
            out.println(jsonObject);
        } else {
            user = new User(req.getParameter("email"), req.getParameter("password"), "用户");
            druidDemo.add(user);
            user = druidDemo.getUserByEmail(req.getParameter("email"));
            session.setAttribute("user", user);
        }
    }
}
