package day0826;

import com.alibaba.fastjson.JSONObject;
import day0904.MybatisMapper;
import day0904.mybatis.po.User;
import day0904.DruidDemo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/RegisterVerifyServlet")
public class RegisterVerifyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").trim();
        User user = MybatisMapper.mapper.getUserByEmail(email);
        JSONObject jsonObject = new JSONObject();
        if (user != null) {
            jsonObject.put("message", "邮箱已被占用");
        }
        PrintWriter out = resp.getWriter();
        out.println(jsonObject);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
