package day0826;

import com.alibaba.fastjson.JSONObject;
import day0904.mybatis.po.User;
import day0905.IUserDau;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login-processing")
public class LoginProcessing extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserDau connector = (IUserDau) req.getSession().getServletContext().getAttribute("connector");
        User user = connector.getUserByEmail(req.getParameter("email"));
        JSONObject jsonObject = new JSONObject();
        if(user == null){
            jsonObject.put("message", "未找到该用户！");
        }else{
            jsonObject = (JSONObject) JSONObject.toJSON(user);
            if(req.getParameter("password").equals(user.getPassword())){
                if(user.getRole().equals("管理员")){
                    jsonObject.put("message", "管理员");
                }else{
                    jsonObject.put("message", "用户");
                    jsonObject.put("id", user.getId());
                }
                if("true".equals(req.getParameter("isRemember"))){
                    Cookie cookie = new Cookie("email", req.getParameter("email"));
                    Cookie cookie1 = new Cookie("password", req.getParameter("password"));
                    Cookie cookie2 = new Cookie("isChecked", "checked");
                    cookie.setMaxAge(60 * 60 * 12);
                    cookie1.setMaxAge(60 * 60 * 12);
                    cookie2.setMaxAge(60 * 60 * 12);
                    resp.addCookie(cookie);
                    resp.addCookie(cookie1);
                    resp.addCookie(cookie2);
                }else{
                    Cookie cookie = new Cookie("email", null);
                    Cookie cookie1 = new Cookie("password", null);
                    Cookie cookie2 = new Cookie("isChecked", null);
                    cookie.setMaxAge(0);
                    cookie1.setMaxAge(0);
                    cookie2.setMaxAge(0);
                    resp.addCookie(cookie);
                    resp.addCookie(cookie1);
                    resp.addCookie(cookie2);
                }
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
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
