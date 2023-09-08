package day0826;

import day0904.mybatis.po.User;
import day0905.UserDao;
import day0906.MessageSender;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Random;

@WebServlet("/GetEmailCode")
public class GetEmailCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao connector = (UserDao) req.getSession().getServletContext().getAttribute("connector");
        String email = req.getParameter("email");
        User user = connector.getUserByEmail(email);
        if(user == null){
            StringBuilder sVerifyCode = new StringBuilder();
            Random random = new Random();
            for(int i = 0; i < 4; i ++){
                sVerifyCode.append(random.nextInt(10));
            }
            String verifyCode = sVerifyCode.toString();
            MessageSender messageSender = MessageSender.getInstance();
            messageSender.sentMessage(email, "验证码", verifyCode);
            HttpSession session = req.getSession();
            session.setAttribute("verifyCode", verifyCode);
        }else{
            resp.setStatus(400);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
