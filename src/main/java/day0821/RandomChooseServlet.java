package day0821;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Random;

@WebServlet("/random-choose")
public class RandomChooseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] serial = {"Z.png", "H.png"};
        Random random = new Random();
        String theOne = serial[random.nextInt(serial.length)];
        req.setAttribute("theOne", theOne);
        req.getRequestDispatcher("./result").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
