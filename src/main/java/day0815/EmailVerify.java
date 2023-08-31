package day0815;

import day0818.JDBCConnection;
import day0818.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/emailVerify")
public class EmailVerify extends HttpServlet {
    JDBCConnection jdbcConnection = new JDBCConnection();
    Connection connection = jdbcConnection.getConnection();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").trim();
        User user = jdbcConnection.getUserByEmail(connection, email);
        if(user != null){
            resp.addHeader("isEmailTaken", "true");
        }
    }
}
