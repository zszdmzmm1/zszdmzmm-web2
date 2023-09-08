package day0815;

import day0904.mybatis.po.User;
import day0904.UserDaoJDBCImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/emailVerify")
public class EmailVerify extends HttpServlet {
    UserDaoJDBCImpl userDaoJDBCImpl = UserDaoJDBCImpl.getUserDaoJDBC();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").trim();
        User user = userDaoJDBCImpl.getUserByEmail(email);
        if(user != null){
            resp.addHeader("isEmailTaken", "true");
        }
    }
}
