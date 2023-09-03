package day0831;

import com.mysql.cj.Session;
import day0818.JDBCConnection;
import day0818.User;
import day0818.UserLog;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

//@WebFilter("/logout")
public class LogoutFilter extends HttpFilter {
    JDBCConnection jdbcConnection = JDBCConnection.getJDBCConnection();
    Connection connection = JDBCConnection.getConnection();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        chain.doFilter(req, res);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sDate = simpleDateFormat.format(date);
        int userId = Integer.parseInt(user.getId().substring(1));
        UserLog userLog = new UserLog(userId, sDate, "登出");
        jdbcConnection.addUserLog(connection, userLog);
    }
}
