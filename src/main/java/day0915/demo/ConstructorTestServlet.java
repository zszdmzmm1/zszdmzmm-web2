package day0915.demo;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Constructor")
public class ConstructorTestServlet extends HttpServlet {
    public ConstructorTestServlet() {
        System.out.println("Constructor....");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ConstructorTestServlet.doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("ConstructorTestServlet.init");
    }

    @Override
    public void destroy() {
        System.out.println("ConstructorTestServlet.destroy");
    }
}
