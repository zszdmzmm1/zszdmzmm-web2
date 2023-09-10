package day0826;

import com.alibaba.fastjson.JSONObject;
import day0904.mybatis.po.User;
import day0908.MessageDTO;
import day0908.UserService;
import day0908.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login-processing")
public class LoginProcessing extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserService userService = UserServiceImpl.getInstance();
        MessageDTO messageDTO = userService.loginProcessingService(email, password);
        JSONObject jsonObject = new JSONObject();
        if (messageDTO.getCode() == 100) {
            User user = (User) messageDTO.getObject();
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            //jsonObject = (JSONObject) JSONObject.toJSON(user);
            jsonObject.put("id", user.getId());
            if ("true".equals(req.getParameter("isRemember"))) {
                Cookie cookie = new Cookie("email", req.getParameter("email"));
                Cookie cookie1 = new Cookie("password", req.getParameter("password"));
                Cookie cookie2 = new Cookie("isChecked", "checked");
                cookie.setMaxAge(60 * 60 * 12);
                cookie1.setMaxAge(60 * 60 * 12);
                cookie2.setMaxAge(60 * 60 * 12);
                resp.addCookie(cookie);
                resp.addCookie(cookie1);
                resp.addCookie(cookie2);
            } else {
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
        }
        jsonObject.put("message", messageDTO.getMessage());
        PrintWriter out = resp.getWriter();
        out.println(jsonObject);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
