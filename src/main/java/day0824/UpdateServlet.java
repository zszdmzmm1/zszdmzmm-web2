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


@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    JDBCConnection jdbcConnection =  new JDBCConnection();
    Connection connection = jdbcConnection.getConnection();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id").substring(1));
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = jdbcConnection.getUserById(connection, id);
        if("".equals(email)){
            email = user.getEmail();
        }
        if("".equals(password)){
            password = user.getPassword();
        }
        jdbcConnection.update(connection, id, email, password);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
