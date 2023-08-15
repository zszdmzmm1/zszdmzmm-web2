package day0815;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/verify")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("get...");
        System.out.println(req.getMethod());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("email").equals("admin@qq.com") && req.getParameter("password").equals("secret")){
            System.out.println("登陆成功");
            resp.sendRedirect("./index.html");
        }else{
            System.out.println("登陆失败");
        }
    }
}

