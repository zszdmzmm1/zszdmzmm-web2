package day0831;

import day0904.mybatis.po.User;
import day0904.mybatis.po.UserLog;
import day0905.IUserDau;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@WebFilter("/logout")
public class LogoutFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        IUserDau connector = (IUserDau) req.getSession().getServletContext().getAttribute("connector");
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        chain.doFilter(req, res);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sDate = simpleDateFormat.format(date);
        int userId = Integer.parseInt(user.getId().substring(1));
        UserLog userLog = new UserLog(userId, sDate, "登出");
        connector.addUserLog(userLog);
    }
}
