package day0826;

import com.alibaba.fastjson.JSONObject;
import day0818.JDBCConnection;
import day0818.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/RegisterVerifyServlet")
public class RegisterVerifyServlet extends HttpServlet {
    JDBCConnection jdbcConnection = new JDBCConnection();
    Connection connection = jdbcConnection.getConnection();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").trim();
        User user = jdbcConnection.getUserByEmail(connection, email);
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
