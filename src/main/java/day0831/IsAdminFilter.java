package day0831;

import day0904.mybatis.po.User;
import day0904.mybatis.po.UserLog;

import day0905.UserDao;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;


@WebFilter("/admin/user")
public class IsAdminFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        UserDao connector = (UserDao) req.getSession().getServletContext().getAttribute("connector");
        HttpSession session = req.getSession();
        User user =(User)session.getAttribute("user");
        if(user == null || !"管理员".equals(user.getRole())){
            res.sendRedirect("../login");
            return;
        }
        req.setAttribute("user", user);
        chain.doFilter(req, res);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sDate = simpleDateFormat.format(date);
        int userId = Integer.parseInt(user.getId().substring(1));
        UserLog userLog = new UserLog(userId, sDate, "管理员登录");
        connector.addUserLog(userLog);
    }
}
