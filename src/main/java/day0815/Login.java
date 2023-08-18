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
            req.setAttribute("email", req.getParameter("email"));
            req.getRequestDispatcher("./welcome-page").forward(req, resp);
        }else{
            req.setAttribute("email", "登陆失败");
            req.getRequestDispatcher("./welcome-page").forward(req, resp);
        }
    }
}

