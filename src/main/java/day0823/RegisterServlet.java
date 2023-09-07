package day0823;

import com.alibaba.fastjson.JSONObject;
import day0904.mybatis.po.User;
import day0905.IUserDau;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserDau connector = (IUserDau) req.getSession().getServletContext().getAttribute("connector");
        User user = connector.getUserByEmail(req.getParameter("email"));
        HttpSession session = req.getSession();
        JSONObject jsonObject = new JSONObject();
        if (user != null) {
            jsonObject.put("message", "该用户已存在");
            session.setAttribute("user", null);
        } else {
            user = new User(req.getParameter("email"), req.getParameter("password"), "用户");
            connector.addUser(user);
            user = connector.getUserByEmail(req.getParameter("email"));
            session.setAttribute("user", user);
            jsonObject.put("message", "success");
        }
        PrintWriter out = resp.getWriter();
        out.println(jsonObject);
    }
}
