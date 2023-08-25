package day0824;

import day0818.JDBCConnection;
import day0818.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    JDBCConnection jdbcConnection =  new JDBCConnection();
    Connection connection = jdbcConnection.getConnection();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if("".equals(email) || "".equals(password)){
            resp.sendError(400);
            return;
        }
        User user = new User(email, password, "");
        jdbcConnection.add(connection, user);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
