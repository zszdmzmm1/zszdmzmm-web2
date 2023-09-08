package day0823;

import com.alibaba.fastjson.JSONObject;
import day0904.mybatis.po.User;
import day0908.MessageDTO;
import day0908.UserService;
import day0908.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registerProcessing")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = new User(email, password, "用户");
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = req.getSession();
        MessageDTO messageDTO = userService.registerProcessingService(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", messageDTO.getMessage());
        session.setAttribute("user", messageDTO.getObject());
        PrintWriter out = resp.getWriter();
        out.println(jsonObject);
    }
}
