package day0824;

import day0905.IUserDau;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/deleteUsers")
public class DeleteUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IUserDau connector =(IUserDau) req.getSession().getServletContext().getAttribute("connector");
        String sId = req.getParameter("sId");
        String[] idArray = sId.split(",");
        List<String> idList = new ArrayList<>(List.of(idArray));
        for (String id: idList) {
            connector.deleteUser(Integer.parseInt(id.substring(1)));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
