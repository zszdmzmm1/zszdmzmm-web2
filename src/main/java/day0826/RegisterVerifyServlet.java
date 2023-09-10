package day0826;

import com.alibaba.fastjson.JSONObject;
import day0908.MessageDTO;
import day0908.UserService;
import day0908.UserServiceImpl;
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
        UserService userService = UserServiceImpl.getInstance();
        MessageDTO messageDTO = userService.registerVerify(email);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", messageDTO.getMessage());
        PrintWriter out = resp.getWriter();
        out.println(jsonObject);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
