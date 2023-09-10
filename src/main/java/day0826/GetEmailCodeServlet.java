package day0826;

import day0906.MessageSender;
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

@WebServlet("/GetEmailCode")
public class GetEmailCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        UserService userService = UserServiceImpl.getInstance();
        MessageDTO messageDTO = userService.getVerifyCode(email);
        if(messageDTO.getCode() == 100){
            String verifyCode = (String) messageDTO.getObject();
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
