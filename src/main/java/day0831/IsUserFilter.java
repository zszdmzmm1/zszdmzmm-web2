package day0831;

import day0904.MybatisMapper;
import day0904.mybatis.po.User;
import day0904.mybatis.po.UserLog;
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

@WebFilter("/user/*")
public class IsUserFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null){
            res.sendRedirect("../login");
            return;
        }
        req.setAttribute("user", user);
        chain.doFilter(req, res);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String sDate = simpleDateFormat.format(date);
        int userId = Integer.parseInt(user.getId().substring(1));
        UserLog userLog = new UserLog(userId, sDate, "用户登录");
        MybatisMapper.mapper.addUserLog(userLog);
    }
}
