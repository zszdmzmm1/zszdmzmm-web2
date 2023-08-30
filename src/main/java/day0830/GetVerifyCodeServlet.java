package day0830;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/getVerifyCodeServlet")
public class GetVerifyCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String fromVerifyCode = (String)session.getAttribute("verifyCode");
        String toVerifyCode = req.getParameter("verifyCode");
        PrintWriter out = resp.getWriter();
        JSONObject jsonObject =  new JSONObject();
        if(!fromVerifyCode.equals(toVerifyCode)){
            jsonObject.put("message", "验证码错误");
        }else{
            jsonObject.put("message", "验证码通过");
        }
        out.println(jsonObject);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
