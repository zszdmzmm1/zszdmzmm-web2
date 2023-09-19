package day0918.bobing.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/setDiceCount")
public class SetDiceCount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = req.getParameter("from");
        String money = req.getParameter("money");
        int count = Integer.parseInt(req.getParameter("count"));
        if("dice".equals(from)) {
            if(count > 0){
                count -= 1;
            }
        }
        HttpSession session = req.getSession();
        session.setAttribute("from", from);
        session.setAttribute("money", money);
        session.setAttribute("count", count);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
