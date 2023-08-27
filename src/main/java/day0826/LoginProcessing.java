package day0826;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
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

@WebServlet("/login-processing")
public class LoginProcessing extends HttpServlet {
    JDBCConnection jdbcConnection = new JDBCConnection();
    Connection connection = jdbcConnection.getConnection();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = jdbcConnection.getUserByEmail(connection, req.getParameter("email"));
        JSONObject jsonObject = new JSONObject();
        if(user == null){
            jsonObject.put("message", "未找到该用户！");
        }else{
            jsonObject = (JSONObject) JSONObject.toJSON(user);
            if(req.getParameter("password").equals(user.getPassword())){
                jsonObject.put("message", "验证成功！");
            } else {
                jsonObject.put("message", "密码错误！");
            }
        }
        PrintWriter out = resp.getWriter();
        out.println(jsonObject);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
