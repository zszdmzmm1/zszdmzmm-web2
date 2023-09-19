package day0918.bobing.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/diceImg")
public class ImgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String point = req.getParameter("point");
        String url = this.getServletContext().getRealPath("./image/dice_" + point + ".png");
        FileInputStream fis = new FileInputStream(url);
        resp.setContentType("image/png");
        OutputStream sos = resp.getOutputStream();
        IOUtils.copy(fis, sos);
        fis.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
